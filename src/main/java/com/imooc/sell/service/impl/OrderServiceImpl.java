package com.imooc.sell.service.impl;

import com.imooc.sell.converter.OrderMaster2OrderDTOConverter;
import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.dataobject.OrderMaster;
import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.dto.CartDTO;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SelleException;
import com.imooc.sell.repository.OrderDetailRepository;
import com.imooc.sell.repository.OrderMasterRepository;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.service.ProductService;
import com.imooc.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * Created by niuchengming
 *
 * @ Date: 2019/6/19 15:21
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        //生成订单编号
        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

       // List<CartDTO> cartDTOList =new ArrayList<>();

        //1。查询商品（数量，价格）
        for(OrderDetail orderDetail:orderDTO.getOrderDetailList()){

            ProductInfo productInfo =productService.findOne(orderDetail.getProductId());
            if (productInfo == null){
                throw new SelleException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算订单总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            //订单详情入库

            //对象拷贝，将productInfo里面的属性拷贝到orderDetail
            //
            BeanUtils.copyProperties(productInfo, orderDetail);

            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);  //orderId在整个订单创建的时候就已经生成了

            orderDetailRepository.save(orderDetail);

            //CartDTO cartDTO = new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity());
            //cartDTOList.add(cartDTO);
        }

        //3.写入订单数据库（orderMaster和orderDetail）

        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);

        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        //4，扣库存,通过lambda表达式可以使用tolist直接返回catDTO的List,
        // 不用先定义，再创建再add进去

        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(),e.getProductQuantity())
                ).collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findById(orderId).orElse(null);
        if (orderMaster == null){
            throw new SelleException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)){
            throw new SelleException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());
        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());
        return orderDTOPage;
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();


        //判断订单的状态,订单的状态必须等于新下的订单，才能取消，如说是完结的状态则不能取消
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【取消订单】订单状态不正确，orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SelleException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null){
            log.error("【取消订单】更新失败，orderMaster={}",orderMaster);
            throw new SelleException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        //返还库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【取消订饭】订单中无商品详情,orderDTO={}",orderDTO);
            throw new SelleException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e ->new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartDTOList);

        //如果已支付，需要给用户退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
            //TODO

        }

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {

        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【完结订单】完结订单不正确，orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SelleException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null){
            log.error("【完结订单】完结订单失败，orderMaster={}",orderMaster);
            throw new SelleException(ResultEnum.ORDER_UPDATE_FAIL);
        }


        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【订单支付成功】订单支付不正确，orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SelleException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.error("【订单支付完成】订单支付状态不正确，orderDTO",orderDTO);
            throw new SelleException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改支付状态

        //修改状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null){
            log.error("【订单支付完成】更新失败，orderMaster={}",orderMaster);
            throw new SelleException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        return orderDTO;
    }


    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());
        return new PageImpl<>(orderDTOList,pageable,orderMasterPage.getTotalElements());
    }
}

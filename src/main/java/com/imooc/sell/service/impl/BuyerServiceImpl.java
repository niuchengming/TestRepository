package com.imooc.sell.service.impl;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SelleException;
import com.imooc.sell.service.BuyerService;
import com.imooc.sell.service.OrderService;
import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Created by niuchengming
 *
 * @ Date: 2019/6/24 17:34
 */
@Slf4j
@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrderOne(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if (orderDTO == null){
            log.error("【取消订单】 查不到该订单，orderId={}",orderId);
            throw new SelleException(ResultEnum.ORDER_NOT_EXIST);
        }

        return orderService.cancel(orderDTO);
    }

    private  OrderDTO checkOrderOwner(String openid,String orderId){
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null){
            return null;
        }
        //判断是否是自己的订单
        if (orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)){
            log.error("【查询订单】 订单的openid。openid={},orderId={}",openid,orderId);
            throw new SelleException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}

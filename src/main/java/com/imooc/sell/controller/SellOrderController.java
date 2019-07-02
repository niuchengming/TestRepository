package com.imooc.sell.controller;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SelleException;
import com.imooc.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by niuchengming
 *
 * @ Date: 2019/6/26 9:43
 */
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellOrderController {

    @Autowired
    private OrderService orderService;

    /**
     *订单列表
     * @param page 第几页，从1页开始
     * @param size 一共有多少条数据
     * @return
     * */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map){
        PageRequest request = PageRequest.of(page-1,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        map.put("orderDTOPage",orderDTOPage);
        return new ModelAndView("order/list",map);
    }

    /**
     * 取消订单
     * */
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,
                               Map<String,Object> map){

        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        } catch (SelleException e){
            log.error("【卖家端取消订单】查询不到商品");
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }

        map.put("msg", ResultEnum.SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("order/cancel");

    }

}

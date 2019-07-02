package com.imooc.sell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created by niuchengming
 *
 * @ Date: 2019/6/24 8:53
 */
@Data
public class OrderForm {

    /**买家姓名*/
    @NotEmpty(message = "姓名必填")
    private String name;

    /**买家手机号*/
    @NotEmpty(message = "手机号必填")
    private String phone;

    /**买家地址*/
    @NotEmpty(message = "地址必填")
    private String address;

    /**买家微醺openId*/
    @NotEmpty(message = "买家微信 openId必填")
    private String openId;

    /**购物车*/
    @NotEmpty(message = "购物车不能为空")
    private String items;

}

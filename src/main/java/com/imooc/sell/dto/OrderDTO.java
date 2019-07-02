package com.imooc.sell.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * 数据传输对象
 * Created by niuchengming
 *
 * @ Date: 2019/6/19 15:14
 */
@Data
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_NULL)  //返回给前端的为Null的不显示
public class OrderDTO {

    /**订单ID. */
    private String orderId;

    /**买家名字 */
    private String buyerName;

    /**买家手机号 */
    private String buyerPhone;

    /**买家地址 */
    private String buyerAddress;

    /**买家微信ID */
    private String buyerOpenid;

    /**订单总金额 */
    private BigDecimal orderAmount;

    /**订单状态，默认为新下单 */
    private Integer orderStatus;

    /**支付状态 ,默认为0未支付 */
    private Integer payStatus;

    /**创建时间 */
    private Date createTime;

    /**更新时间 */
    private Date updateTime;

    List<OrderDetail> orderDetailList;

}

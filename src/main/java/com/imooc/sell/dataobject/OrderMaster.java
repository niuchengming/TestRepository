package com.imooc.sell.dataobject;

import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * 订单主表
 * Created by niuchengming
 *
 * @ Date: 2019/6/17 15:45
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    /**订单ID. */
    @Id
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
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /**支付状态 ,默认为0未支付 */
    private Integer payStatus= PayStatusEnum.WAIT.getCode();

    /**创建时间 */
    private Date createTime;

    /**更新时间 */
    private Date updateTime;


}

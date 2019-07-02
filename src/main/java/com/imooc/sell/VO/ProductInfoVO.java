package com.imooc.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by niuchengming
 *
 * @ Date: 2019/6/14 18:01
 */
@Data
public class ProductInfoVO implements Serializable {

    private static final long serialVersionUID = 865593565441114121L;
    //@JsonProperty里面是返回给前端的字段，
    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDescreption;

    @JsonProperty("icon")
    private String productIcon;

}

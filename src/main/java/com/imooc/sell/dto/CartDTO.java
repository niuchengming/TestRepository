package com.imooc.sell.dto;

import lombok.Data;

/**
 * 购物车
 * Created by niuchengming
 *
 * @ Date: 2019/6/19 20:18
 */
@Data
public class CartDTO {

    /**商品Id. */
    private String productId;

    /**数量. */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}

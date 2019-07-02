package com.imooc.sell.enums;

import lombok.Getter;
import org.omg.PortableInterceptor.INACTIVE;

/**
 * 商品状态
 * Created by niuchengming
 *
 * @ Date: 2019/6/14 15:35
 */
@Getter
public enum ProductStatusEnum {

    /**
     * 0表示上架，1表示下架
     */
    UP(0, "上架"),
    DOWN(1, "下架");
    private Integer code;

    private String message;

    ProductStatusEnum(Integer code,String message) {
        this.code = code;
        this.message = message;
    }

}
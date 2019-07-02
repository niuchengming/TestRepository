package com.imooc.sell.enums;

import lombok.Getter;

/**
 * Created by niuchengming
 *
 * @ Date: 2019/6/17 15:55
 */
@Getter
public enum PayStatusEnum {
    WAIT(0, "等待支付"),
    SUCCESS(1,"支付成功"),
    ;

    private Integer code;
    private String message;
    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

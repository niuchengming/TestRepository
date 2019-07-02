package com.imooc.sell.exception;

import com.imooc.sell.enums.ResultEnum;
import lombok.Getter;

/**
 * Created by niuchengming
 *
 * @ Date: 2019/6/19 15:59
 */
@Getter
public class SelleException extends RuntimeException{
    private Integer code;

    public SelleException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SelleException(Integer code, String message){
        super(message);
        this.code = code;
    }

}

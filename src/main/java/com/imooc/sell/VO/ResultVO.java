package com.imooc.sell.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * http请求返回的最外层对象
 * Created by niuchengming
 *
 * @ Date: 2019/6/14 16:54
 */
@Data
public class ResultVO<T> implements Serializable {


    private static final long serialVersionUID = 8848731630266574133L;
    /**错误码*/
    private Integer code;

    /** 提示信息 */
    private String msg;

    /** 具体内容 ，可以用泛型表示*/
    private T data;
}

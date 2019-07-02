package com.imooc.sell.handler;

import com.imooc.sell.VO.ResultVO;
import com.imooc.sell.exception.SelleException;
import com.imooc.sell.utils.ResultVOUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by niuchengming
 *
 * @ Date: 2019/6/27 10:02
 */
public class SellExceptionHandler {


    //统一异常，使报的错误跟我们自己定义的错误格式一致
    @ExceptionHandler(value = SelleException.class)
    @ResponseBody
    public ResultVO handlerSellerException(SelleException e){
        return ResultVOUtil.error(e.getCode(),e.getMessage());
    }
}

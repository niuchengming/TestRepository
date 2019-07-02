package com.imooc.sell.utils;

import com.imooc.sell.VO.ResultVO;

/**
 * Created by niuchengming
 *
 * @ Date: 2019/6/14 20:10
 */

public class ResultVOUtil {

    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

    public static ResultVO success(){
        return success(null);
    }

    public static ResultVO error(Integer code, String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}

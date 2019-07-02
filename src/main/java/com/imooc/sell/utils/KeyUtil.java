package com.imooc.sell.utils;

import java.util.Random;

/**
 * Created by niuchengming
 *
 * @ Date: 2019/6/19 16:20
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式：时间+随机数
     * @return
     */

    public static synchronized String genUniqueKey(){
        Random random = new Random();
        //当前的毫秒数
        System.currentTimeMillis();

        //生成6位随机数
        Integer number = random.nextInt(900000)+100000;
        return System.currentTimeMillis()+String.valueOf(number);
    }
}

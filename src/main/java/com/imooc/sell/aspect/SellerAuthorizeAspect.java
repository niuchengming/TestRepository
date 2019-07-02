//package com.imooc.sell.aspect;
//
//import com.imooc.sell.exception.SelleException;
//import com.imooc.sell.exception.SellerAuthorizeException;
//import jdk.nashorn.internal.ir.RuntimeNode;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//
///**
// * Created by niuchengming
// *
// * @ Date: 2019/6/26 17:53
// */
//@Aspect
//@Component
//@Slf4j
//public class SellerAuthorizeAspect {
//
//
//
//    @Pointcut("execution(public * com.imooc.sell.controller.sell*.*(..))" +
//            "&& !execution(public * com.imooc.sell.controller.Sell*.*(..))")
//    public void verify(){}
//
//    @Before("verify()")
//    public void doVerify(){
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//
////        //查询cookie
////        Cookie cookie = CookieUtil.get(request,CookieConstant.TOKEN);
////        if (cookie == null){
////            log.warn("【登录校验】Cookie中查不到token");
////            throw new SellerAuthorizeException();
////
////        }
////    }
//}

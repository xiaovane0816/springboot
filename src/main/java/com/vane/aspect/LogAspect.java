package com.vane.aspect;

import org.apache.ibatis.binding.MapperMethod;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.weaver.ast.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by wenshaobo on 2018/6/27.
 */
@Aspect
@Component
public class LogAspect {
    Logger logger = LoggerFactory.getLogger(LogAspect.class);
    @Pointcut("execution(public * com.vane.web.*Controller.*(..))")
    private void pointCut(){}

    @Before("pointCut()")
    public void beforeLog(){
        System.out.println("");
        System.out.println("####################before");
    }

    @Around("pointCut()")
    public Map beforeLog(JoinPoint joinPoint) throws Throwable {
        Map<String,Object> map1 = new HashMap<>();
        System.out.println("##################before");
        logger.info("前置通知");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        TestAspect testAspect = (TestAspect) methodSignature.getMethod().getAnnotations()[1];
        logger.info("request URL: " + request.getRequestURL().toString());
        logger.info("request URI: " + request.getRequestURI().toString());
        logger.info("Http_method: " + request.getMethod());
        logger.info("IP: " + request.getRemoteAddr());
        logger.info("CLASS_METHOD: " + methodSignature.getMethod());
        logger.info("Annotations : " + testAspect.value());
        if(testAspect.value().equals("areaId1")){
            map1.put("test","helloworld_cutDown");
            return map1;
        }

        ProceedingJoinPoint joinPoint1 = (ProceedingJoinPoint) joinPoint;
        map1.put("data",joinPoint1.proceed());
        return map1;
//        Enumeration<String> enumeration = request.getParameterNames();
//        Map<String,Object> map = new HashMap<>();
//        while (enumeration.hasMoreElements()){
//            String parameter = enumeration.nextElement();
//            map.put(parameter,request.getParameter(parameter));
//        }
//        logger.info("enumeration_ARGS: " + map);
//        logger.info("parameterM111ap_ARGS: " + request.getParameterMap().toString());
//        joinPoint.getThis();
//        return map1;
    }
}
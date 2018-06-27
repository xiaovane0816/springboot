package com.vane.aspect;


import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created by wenshaobo on 2018/6/27.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAspect {
    String value() default "";

}

package com.sys.dao.mybatis;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created by fanxiyue on 15/7/6.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface MyBatisRepository {
    String value() default "";
}

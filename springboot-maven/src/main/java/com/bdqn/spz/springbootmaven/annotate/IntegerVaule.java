/**
 * 
 */
package com.bdqn.spz.springbootmaven.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @描述 springboot-maven
 * @作者 施鹏振
 * @创建日期 2018年4月6日
 * @创建时间 上午11:57:10
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IntegerVaule {
	int value() default 0;
    String name() default "";
}

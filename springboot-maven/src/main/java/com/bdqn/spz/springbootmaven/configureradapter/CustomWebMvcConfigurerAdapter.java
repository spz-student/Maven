/**
 * 
 */
package com.bdqn.spz.springbootmaven.configureradapter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.bdqn.spz.springbootmaven.interceptor.SimpleHandlerInterceptor;

/**
 * @描述 拦截器
 * @作者 施鹏振
 * @创建日期 2018年4月5日
 * @创建时间 下午8:30:41
 */
@Configuration // 标注此文件为一个配置项，spring boot才会扫描到该配置。该注解类似于之前使用xml进行配置
public class CustomWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 这个链接来的请求进行拦截
		registry.addInterceptor(new SimpleHandlerInterceptor()).addPathPatterns("/");
	}

}

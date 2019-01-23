/**
 * 
 */
package com.bdqn.spz.springbootmaven.configureradapter;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @描述 配置加载时的页面
 * @作者 施鹏振
 * @创建日期 2018年4月6日
 * @创建时间 上午10:38:03
 */
@Configuration
public class DefailtViewConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry){
		registry.addRedirectViewController("/","login");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		super.addViewControllers(registry);
	}
}

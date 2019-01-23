package com.bdqn.spz.springbootmaven.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * druid 配置.
 * 这样的方式不需要添加注解：@ServletComponentScan
 * @author 施鹏振
 */
@Configuration
public class DruidConfig {

	@Bean
	public ServletRegistrationBean DruidStaViewServle(){
		//org.springframework.boot.web.servlet.ServletRegistrationBean提供类的进行注册
		ServletRegistrationBean servletRegistrationBean=new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
		//添加初始化参数:initparams
		//白名单
		servletRegistrationBean.addInitParameter("allow","192.168.1.72,127.0.0.1");
		//IP黑名单 (存在共同时，deny优先于allow): 如果满足deny的话提示:Sorry, you are not permitted to view this page.
		servletRegistrationBean.addInitParameter("deny","192.168.102.73");
		//登录查看信息的账号密码
		servletRegistrationBean.addInitParameter("loginUsername","spz");
		servletRegistrationBean.addInitParameter("loginPassword","shipengzhen1997");
		//是否能过重置数据
		servletRegistrationBean.addInitParameter("resetEnable","false");//serialVersionUID
		return servletRegistrationBean;
	}
	
	/**
	 * 注册一个:filterRegistrationBean
	 * @return
	 */
	@Bean
	public FilterRegistrationBean druidStaFilter(){
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
		//添加过滤规则
		filterRegistrationBean.addUrlPatterns("/*");
		//添加不需要忽略的格式信息
		filterRegistrationBean.addInitParameter("exclusions","*.js,*gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}
}

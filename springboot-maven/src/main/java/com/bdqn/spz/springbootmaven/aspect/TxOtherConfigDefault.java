/**
 * 
 */
package com.bdqn.spz.springbootmaven.aspect;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * Created by guozp on 2017/8/28. ??
 */
//@Component
//@Configuration//事务失败
public class TxOtherConfigDefault {

	@Autowired
	private DataSource dataSource;

	// @Bean
	// @ConditionalOnMissingBean
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}

	@Autowired
	private DataSourceTransactionManager transactionManager;

	@Bean
	public TransactionInterceptor transactionInterceptor() {
		Properties attributes = new Properties();
		attributes.setProperty("get*", "PROPAGATION_REQUIRED");
		attributes.setProperty("add*", "PROPAGATION_REQUIRED");
		attributes.setProperty("update*", "PROPAGATION_REQUIRED");
		attributes.setProperty("delete*", "PROPAGATION_REQUIRED");
		TransactionInterceptor txAdvice = new TransactionInterceptor(transactionManager, attributes);
		return txAdvice;
	}

	@Bean
	public AspectJExpressionPointcut aspectJExpressionPointcut() {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		String transactionExecution = "execution (* com.bdqn.spz.springbootmaven.service.*.*(..))";
		pointcut.setExpression(transactionExecution);
		return pointcut;
	}

	@Bean
	public DefaultPointcutAdvisor defaultPointcutAdvisor() {
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
		advisor.setPointcut(aspectJExpressionPointcut());
		advisor.setAdvice(transactionInterceptor());
		return advisor;
	}
}

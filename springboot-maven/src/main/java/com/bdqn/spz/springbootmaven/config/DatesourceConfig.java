package com.bdqn.spz.springbootmaven.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import com.github.pagehelper.PageHelper;

@EnableAutoConfiguration // 也是借助@Import的帮助，将所有符合自动配置条件的bean定义加载到IoC容器
@ComponentScan // 扫秒注解
public class DatesourceConfig implements EnvironmentAware {

	private RelaxedPropertyResolver relaxedPropertyResolver;

	@Override
	public void setEnvironment(Environment environment) {
		this.relaxedPropertyResolver = new RelaxedPropertyResolver(environment, "spring.datasource.");
	}

	// DataSource配置方式一需要继承EnvironmentAware
	// @Bean(destroyMethod = "close", initMethod = "init")
	// public DataSource dataSource() {
	// System.out.println("注入druid！！！");
	// DruidDataSource druidDataSource = new DruidDataSource();
	// druidDataSource.setUrl(relaxedPropertyResolver.getProperty("url"));
	// druidDataSource.setDriverClassName(relaxedPropertyResolver.getProperty("driver-class-name"));
	// druidDataSource.setUsername(relaxedPropertyResolver.getProperty("username"));
	// druidDataSource.setPassword(relaxedPropertyResolver.getProperty("password"));
	// druidDataSource.setMaxActive(Integer.parseInt(relaxedPropertyResolver.getProperty("max-active")));
	// //
	// //druidDataSource.setMaxIdle(Integer.parseInt(relaxedPropertyResolver.getProperty("max-idle")));
	// druidDataSource.setMinIdle(Integer.parseInt(relaxedPropertyResolver.getProperty("min-idle")));
	// return druidDataSource;
	// }

	// DataSource配置方式二
	// 表示将根据前缀“spring.datasource”从application.properties中匹配相关属性值

//	@Bean
//	@ConfigurationProperties(prefix = "spring.datasource")
//	public DataSource dataSource() {
//		return new org.apache.tomcat.jdbc.pool.DataSource();
//	}

	@Resource  
    private DataSource dataSource;  
	
	
	public DataSource dataSource() {
		return dataSource;
	}
	// 提供SqlSeesion
	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		
		//分页插件  
        PageHelper pageHelper = new PageHelper();  
        Properties props = new Properties();  
        props.setProperty("reasonable", "true");  
        props.setProperty("supportMethodsArguments", "true");  
        props.setProperty("returnPageInfo", "check");  
        props.setProperty("params", "count=countSql");  
        pageHelper.setProperties(props);  
        //添加插件  
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});
        
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//      sqlSessionFactoryBean.setConfigLocation(resolver.getResource("classpath:mybatis-config.xml"));  
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/*.xml"));
		sqlSessionFactoryBean.setTypeAliasesPackage("com.bdqn.spz.springbootmaven.pojo");
		return sqlSessionFactoryBean.getObject();
	}

//	@Bean
//	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
//		return new SqlSessionTemplate(sqlSessionFactory);
//	}

	// 提供事务
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

}

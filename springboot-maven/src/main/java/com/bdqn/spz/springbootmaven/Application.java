package com.bdqn.spz.springbootmaven;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.bdqn.spz.springbootmaven.config.DatesourceConfig;

/*
 * @EnableAutoConfiguration//这个注释告诉SpringBoot“猜”你将如何想配置Spring,
 * 基于你已经添加jar依赖项。如果spring-boot-starter-web已经添加Tomcat和Spring MVC,
 * 这个注释自动将假设您正在开发一个web应用程序并添加相应的spring设置。
 * 自动配置被设计用来和“Starters”一起更好的工作,但这两个概念并不直接相关。
 * 您可以自由挑选starter依赖项以外的jar包，springboot仍将尽力自动配置您的应用程序。
*/
@SpringBootApplication(scanBasePackages="com.bdqn.spz.springbootmaven",exclude = {DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})//默认扫描项目下的所有注解
/*
 * Spring Boot项目的核心注解，主要目的是开启自动配置,使用@SpringbootApplication注解.
 * 可以解决根类或者配置类（我自己的说法，就是main所在类）头上注解过多的问题，
 * 一个@SpringbootApplication相当于@Configuration,@EnableAutoConfiguration和 
 * 
 * @ComponentScan("com.bdqn.spz.SpringBootMaven.controller")//默认扫描项目下的所有注解
 */
//@ServletComponentScan//注解方式实现druid必须加
@EnableTransactionManagement//启动注解事务管理(扫描事务注解)
@CacheConfig//支持缓存=这三个:@cache(“something");这个相当于save()操作,@cachePut相当于Update()操作,只要他标示的方法被调用,那么都会缓存起来,
//而@cache则是先看下有没已经缓存了,然后再选择是否执行方法。
//@CacheEvict相当于Delete()操作。用来清除缓存用的。
@MapperScan("com.bdqn.spz.springbootmaven.dao")//扫秒Dao层接口
//@ImportResource(locations={"*.xml"})
public class Application extends DatesourceConfig {

	

	// 在main方法中启动一个应用，即：这个应用的入口
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	//打包package //清除clean //发送到本地仓库install
	//忽略junit测试 maven.test.skip=true //运行junit测试 test
	//上传到私服 deploy
	//设置端口号-server.port=9090//控制台运行jar java -jar C://...*.jar
	//jar上传到本地仓库  
	/*mvn install:install-file 
	-DgroupId=com.oracle 
	-DartifactId=ojdbc6 
	-Dversion=11.2.0.1.0 
	-Dpackaging=jar 
	-Dfile=F:\Learning\Java\jar\oracle-jdbc\ojdbc6-11.2.0.4.jar*/
	//上传jar包到私服
	/*mvn deploy:deploy-file 
	-DgroupId=com.oracle 
	-DartifactId=ojdbc6 
	-Dversion=11.2.0.1.0 
	-Dpackaging=jar 
	-Dfile=F:\Learning\Java\jar\oracle-jdbc\ojdbc6-11.2.0.4.jar
	-Durl=http://localhost:8081/repository/maven-releases/ 
	-DrepositoryId=nexus-releases*/
	
}

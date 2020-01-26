package com.neo.servdao.config;

import java.beans.PropertyVetoException;
import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.logging.log4j.Log4jImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@PropertySource("classpath:mybatis-plus.properties")
@MapperScan("com.neo.servdao.mapper")
@Configuration
public class MybatisPlusConfig {
	
	/**
	 *   
	 */
	@Bean
	public DataSource mysqlDataSource(Environment env) throws PropertyVetoException {
		ComboPooledDataSource combDs = null;
		
		combDs = new ComboPooledDataSource();
		combDs.setDriverClass(env.getProperty("jdbc.driver"));
		combDs.setJdbcUrl(env.getProperty("jdbc.url"));
		combDs.setUser(env.getProperty("jdbc.username"));
		combDs.setPassword(env.getProperty("jdbc.password"));
		
		return combDs;
	}
	
	
	/**
	 * mybatis-plus sqlsessionFactory
	 * @throws Exception 
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory(
			@Autowired DataSource dataSource,
			Environment env) throws Exception {
		MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
		
		//Mybatis打印SQL
		MybatisConfiguration mybatisGlobalConfig = new MybatisConfiguration();
		mybatisGlobalConfig.setLogImpl(Log4jImpl.class);	//指定mybatis日志的具体实现方式
		
		sqlSessionFactoryBean.setConfiguration(mybatisGlobalConfig);
		sqlSessionFactoryBean.setDataSource(dataSource);
		
		//扫描mapper xml
		PathMatchingResourcePatternResolver resourceResol = new PathMatchingResourcePatternResolver();
		Resource[] resources = null;
		try {
			resources = resourceResol.getResources(env.getProperty("mapper.xml.location"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sqlSessionFactoryBean.setMapperLocations(resources);
		return sqlSessionFactoryBean.getObject();
	}
}

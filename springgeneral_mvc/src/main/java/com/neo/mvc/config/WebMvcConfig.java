package com.neo.mvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.neo.mvc.interceptors.CheckWhetherAlreadyLoginInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan("com.neo.mvc")
public class WebMvcConfig implements WebMvcConfigurer {

	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}
	
	
	/**
	 * 配置不被DispatcherServlet拦截的静态资源的请求路径
	 * Config static resources that don't want to be intercepted by DispatcherServlet
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}
	
	/**
	 * 简单的页面跳转器，无需走controller
	 * simple page controller
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
	}
	
	
	/*
	 *  "/static/" path already add into ResourceHandler but still can be intercepted by 
	 *  customized [CheckWhetherAlreadyLoginInterceptor]. But JS file won't.
	 *  it is weird. So I had to set it agagin to [excludePathPatterners].
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		CheckWhetherAlreadyLoginInterceptor cwali = new CheckWhetherAlreadyLoginInterceptor();
		registry.addInterceptor(cwali)
		        .excludePathPatterns("/login","/exit","/signin","/static/**");
	}
}

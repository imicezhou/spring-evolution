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
	
	/*
	 * 简单的页面跳转器，无需走controller
	 * simple page controller
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
	}
	
	
	/*
	 * 配置不被DispatcherServlet拦截的静态资源的请求路径
	 * Config static resources that don't want to be intercepted by DispatcherServlet
	 * 
	 * /pages/** 虽然交给tomcat默认的静态资源处理器来做，但由于下面代码种增加了校验是否登录的拦截器，
	 * 所以，即使这些页面放在WEB-INF外面，用户只要没有登录也无权访问
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
		registry.addResourceHandler("/pages/**").addResourceLocations("/pages/");
	}
	
	
	/*
	 *  "/static/" path already add into ResourceHandler but still can be intercepted by 
	 *  customized [CheckWhetherAlreadyLoginInterceptor]. But JS file won't.
	 *  it is weird. So I had to set it agagin to [excludePathPatterners].
	 *  
	 *  Due to "/pages/" is not included in exclusive patterns, so , guests have no access to
	 *  these html pages without authentication
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		CheckWhetherAlreadyLoginInterceptor cwali = new CheckWhetherAlreadyLoginInterceptor();
		registry.addInterceptor(cwali)
		        .excludePathPatterns("/login","/exit","/signin","/static/**");
	}
}

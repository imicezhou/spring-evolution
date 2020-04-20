package com.neo.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import com.neo.mvc.interceptors.CheckWhetherAlreadyLoginInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan("com.neo.mvc")
public class WebMvcConfig implements WebMvcConfigurer {

	
	
	/**
	 * 1  凡是mv的viewname满足"jsp/*","redirect:*","forward:*"格式的，都交给jsp引擎来做
	 * 2 其他url的view交给thymeleaf来做
	 * 3 所以，一般只有登录页和控制台页交给jsp来做，其他页面都用html格式，需要后台渲染的交给thymeleaf
	 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp").viewNames("jsp/*","redirect:*","forward:*");
		
		
		//添加thymeleaf这个视图解析器
		SpringTemplateEngine ste = new SpringTemplateEngine();
		ste.setTemplateResolver(templateResolver());
		
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(ste);
		viewResolver.setCharacterEncoding("UTF-8");
		
		registry.viewResolver(viewResolver);
	}
	
	
	/*
	 * l简单的页面跳转器，无需走controller
	 * simple page controller
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("jsp/login");
	}
	
	
	/*
	 * 1  配置不被DispatcherServlet拦截的静态资源的请求路径
	 *   Config static resources that don't want to be intercepted by DispatcherServlet
	 * 
	 * 2 /pages/** 虽然交给tomcat默认的静态资源处理器来做，但由于下面代码种增加了校验是否登录的拦截器，
	 * 3  所以，即使这些页面放在WEB-INF外面，用户只要没有登录也无权访问
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
	
	
	/**
	 * Thymeleaf
	 * SpringResourceTemplateResolver needs ServletContext to search template files according to "prefix","suffix","<filename>", 
	 * so it implements ApplicationContextAware to acquire the ServletContext
	 * This bean must be registered into spring IOC container
	 */
	@Bean("resourceTemplateResolver")
	public SpringResourceTemplateResolver templateResolver() {
		
		SpringResourceTemplateResolver srtr = new SpringResourceTemplateResolver();
		srtr.setPrefix("/WEB-INF/template/");
		srtr.setSuffix(".html");
		srtr.setTemplateMode(TemplateMode.HTML);
		srtr.setCacheable(false);
		srtr.setCharacterEncoding("UTF-8");
		
		return srtr;
	}
	
}

package com.neo.servdao.test;

import java.io.IOException;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.common.json.JSONObject;
import com.neo.dubbo.bo.frame.HierarchyMenu;
import com.neo.servdao.config.MybatisPlusConfig;
import com.neo.servdao.config.RootContextConfig;
import com.neo.servdao.mapper.MenuDao;
import com.neo.servdao.mapper.UserDao;
import com.neo.servdao.service.MenuService;
import com.neo.servdao.service.UserService;

public class TestLaunch {

//	@Test
//	public void test01() throws IOException {
//		
//		ClassPathXmlApplicationContext cxac = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/dubbo-provider.xml");
//		cxac.start();
//		String names[] = cxac.getBeanDefinitionNames();
//		for(String name : names) {
//			System.out.println(name);
//		}
//		
//		System.in.read();
//	}
	
	
	@Test
	public void test02() {
		
		AnnotationConfigApplicationContext annoApp = 
				new AnnotationConfigApplicationContext(RootContextConfig.class,MybatisPlusConfig.class);
		
		UserService us = annoApp.getBean(UserService.class);
		HierarchyMenu hm = us.selectUserMenuList(1);
		System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(hm));
		
	}
}

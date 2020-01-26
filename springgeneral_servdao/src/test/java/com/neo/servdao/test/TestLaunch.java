package com.neo.servdao.test;

import java.io.IOException;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestLaunch {

	@Test
	public void test01() throws IOException {
		
		ClassPathXmlApplicationContext cxac = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/dubbo-provider.xml");
		cxac.start();
		String names[] = cxac.getBeanDefinitionNames();
		for(String name : names) {
			System.out.println(name);
		}
		
		System.in.read();
	}
}

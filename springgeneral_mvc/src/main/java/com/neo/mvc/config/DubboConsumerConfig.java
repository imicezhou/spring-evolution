package com.neo.mvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;


@Configuration
@ImportResource("classpath:META-INF/spring/dubbo-consumer.xml")
public class DubboConsumerConfig {

}

package com.snapdeal.springmvc.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebMvc
@ImportResource("classpath:spring-mongo.xml")
@ComponentScan(basePackages = "com.snapdeal.springmvc")
public class HelloWorldConfiguration {
	

}

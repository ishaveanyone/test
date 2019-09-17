package com.dist;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.admin.SpringApplicationAdminMXBean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class Springboot2xxApplication {

	public static void main(String[] args) {
		//使用了建造者模式
		ConfigurableApplicationContext applicationContext= new SpringApplicationBuilder(Springboot2xxApplication.class).web(WebApplicationType.NONE).run(args);
		ApplicationContextUtil.setApplicationContext(applicationContext);
//		SpringApplication.run(Springboot2xxApplication.class, args);
	}

	@Bean
	public ExitCodeGenerator exitCodeGenerator() {
		System.out.println("退出应用");
		return () -> 42;
	}



}

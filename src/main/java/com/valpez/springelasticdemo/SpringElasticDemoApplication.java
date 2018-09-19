package com.valpez.springelasticdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Here ElastichSearch is wrapped inside SpringBoot, so that it starts when 
 * SpringBoot gets started. In other words, here ElasticSearch comes up as 
 * a single instance inside a SpringBoot application. We can also have an 
 * external instance of ElasticSearch, to which we can connect from a
 * SpringBoot application. 
 */
@SpringBootApplication
public class SpringElasticDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringElasticDemoApplication.class, args);
	}
}

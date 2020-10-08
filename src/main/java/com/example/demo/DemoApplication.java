package com.example.demo;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.example.demo.util.DateUtils;

//@SpringBootApplication

@EnableAutoConfiguration
@ComponentScan
public class DemoApplication {

	public static void main(String[] args) {
		/* show all bean is run */
		SpringApplication.run(DemoApplication.class, args);
	}

	/* Bean is < config > init to Bean */
	@Bean
	CommandLineRunner init(ApplicationContext ctx, DateUtils dateUtils) {
		return args -> {
			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}
			System.out.println(dateUtils.todayString());
		};
	}

}

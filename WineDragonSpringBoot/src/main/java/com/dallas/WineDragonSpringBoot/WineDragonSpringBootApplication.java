	package com.dallas.WineDragonSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration(exclude=HibernateJpaAutoConfiguration.class)
@ComponentScan(basePackages = "com.dallas.WineDragonSpringBoot")
public class WineDragonSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(WineDragonSpringBootApplication.class, args);
	}

}

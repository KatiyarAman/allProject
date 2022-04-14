package com.ex.many;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = {"com.ex.many.controller","com.ex.many.service.*",})
public class ManyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManyApplication.class, args);
	}

}

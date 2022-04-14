package com.quokka.map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = {"com.quokka.map.*"})
public class MapApplication {

	public static void main(String[] args) {
		SpringApplication.run(MapApplication.class, args);
	}

}

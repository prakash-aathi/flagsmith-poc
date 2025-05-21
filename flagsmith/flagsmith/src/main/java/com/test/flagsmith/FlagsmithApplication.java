package com.test.flagsmith;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FlagsmithApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlagsmithApplication.class, args);
	}

}

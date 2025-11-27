package com.sparta.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
// @EnableJpaAuditing
@SpringBootApplication(scanBasePackages = {
	"com.sparta.userservice",
	"com.sparta.common.security",
	"com.sparta.common.aop"
})
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}

package com.isi.employe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
public class EmployeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeApplication.class, args);
	}

}

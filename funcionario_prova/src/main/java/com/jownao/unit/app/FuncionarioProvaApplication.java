package com.jownao.unit.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
@EnableJpaRepositories("com.jownao.unit.domain")
@EntityScan("com.jownao.unit.domain")
@ComponentScan("com.jownao.unit.app")
public class FuncionarioProvaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FuncionarioProvaApplication.class, args);
	}

}

package com.hhms;
// driver
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class ComercialApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComercialApplication.class, args);

	}

}

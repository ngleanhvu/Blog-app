package com.ngleanhvu;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Spring boot Blog app REST APIs",
				description = "Spring boot Blog app REST APIs Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Nguyen Le Anh Vu",
						email = "vunguyen.0208200402@gmail.com"
				),
				license = @License(
						name = "Apache 2.0"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring boot Blog app REST APIs Documentation",
				url = "https://github.com/AnhVuCoder/Blog-app.git"
		)
)
public class LearnJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnJavaApplication.class, args);

	}

}
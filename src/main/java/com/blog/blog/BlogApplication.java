package com.blog.blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) {

		SpringApplication.run(BlogApplication.class, args);
		System.out.println("holaaa");
	}

}

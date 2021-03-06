package com.project.human.resource;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan
@SpringBootApplication
public class Application extends SpringBootServletInitializer{
	//This is my applications in kubernet
	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	    	return application.sources(Application.class);
	    }
	public static void main(String[] args) {
	     SpringApplication.run(Application.class, args);
	}

}



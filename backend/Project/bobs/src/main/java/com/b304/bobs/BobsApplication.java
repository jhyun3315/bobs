package com.b304.bobs;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BobsApplication {

	public static void main(String[] args) {

		SpringApplication.run(BobsApplication.class, args);

	}
}

package org.generation.italy.springdemo;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringdemoApplication {
	@Autowired
	private EntityManager em;

	//aggiunto per testare DevTools
	public static void main(String[] args) {

		SpringApplication.run(SpringdemoApplication.class, args);
	}
//aggiunto per testare del tools
}

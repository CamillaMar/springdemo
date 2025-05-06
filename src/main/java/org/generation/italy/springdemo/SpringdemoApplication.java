package org.generation.italy.springdemo;

import jakarta.persistence.EntityManager;
import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.repositories.CustomProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

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

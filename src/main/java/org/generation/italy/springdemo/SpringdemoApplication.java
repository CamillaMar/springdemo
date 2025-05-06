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
public class SpringdemoApplication implements CustomProductRepository {
	@Autowired
	private EntityManager em;

	public static void main(String[] args) {
		SpringApplication.run(SpringdemoApplication.class, args);
	}

	@Override
	public Optional<Product> findById(int id){
		return Optional.ofNullable(em.find(Product.class,id));
	}

	@Override
	public List<Product> findAll() throws DataException {
		return List.of();
	}

	@Override
	public Product save(Product product) {
		return null;
	}

	@Override
	public boolean deleteById(int id) {
		return false;
	}

	@Override
	public boolean update(Product newProduct) {
		return false;
	}

}

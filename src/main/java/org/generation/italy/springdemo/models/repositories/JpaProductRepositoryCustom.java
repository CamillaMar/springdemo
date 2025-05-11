package org.generation.italy.springdemo.models.repositories;

import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.restdtos.ProductFiltersDto;

import java.util.List;

public interface JpaProductRepositoryCustom {
    List<Product> searchProducts(ProductFiltersDto filters);
}

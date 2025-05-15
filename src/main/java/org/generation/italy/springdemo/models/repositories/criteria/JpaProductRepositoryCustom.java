package org.generation.italy.springdemo.models.repositories.criteria;

import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.searchCriteria.ProductFilterCriteria;

import java.util.List;

public interface JpaProductRepositoryCustom {
    List<Product> searchProducts(ProductFilterCriteria filters);
}

package org.generation.italy.springdemo.models.repositories.criteriaRepositories;

import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.exceptions.DataException;

import java.math.BigDecimal;
import java.util.List;

public interface CriteriaProductRepository {
    List<Product> searchProducts(Integer categoryId, Integer supplierId, BigDecimal minPrice, BigDecimal maxPrice) throws DataException;
}

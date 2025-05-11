package org.generation.italy.springdemo.models.repositories;

import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.exceptions.DataException;

import java.math.BigDecimal;
import java.util.List;

public interface CustomProductRepository {
    List<Product> searchAllProducts(Integer supplierId, Integer categoryId, BigDecimal minPrice, BigDecimal maxPrice ) throws DataException;
}

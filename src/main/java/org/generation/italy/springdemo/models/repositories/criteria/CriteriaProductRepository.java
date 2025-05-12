package org.generation.italy.springdemo.models.repositories.criteria;

import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CriteriaProductRepository{
    List<Product> searchProductsFilters(Integer supplierId, Integer categoryId, BigDecimal maxPrice, BigDecimal minPrice, String namePart) throws DataException;
}
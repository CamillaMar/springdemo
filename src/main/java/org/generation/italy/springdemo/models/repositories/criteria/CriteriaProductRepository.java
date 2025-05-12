package org.generation.italy.springdemo.models.repositories.criteria;

import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.searchCriteria.ProductFilterCriteria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CriteriaProductRepository{
    List<Product> searchProductsFilters(ProductFilterCriteria filters) throws DataException;
}

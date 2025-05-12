package org.generation.italy.springdemo.models.repositories.product;

import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.searchcriteria.ProductFilterCriteria;

import java.util.List;

public interface JpaProductRepositoryCustom {
    List<Product> searchProducts(ProductFilterCriteria filters) throws DataException;
}

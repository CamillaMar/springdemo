package org.generation.italy.springdemo.models.repositories;

import org.generation.italy.springdemo.models.dtos.ProductSummary;
import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.repositories.criteria.CriteriaProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface JpaProductRepository extends JpaRepository<Product,Integer> , CriteriaProductRepository {
    List<Product> findByProductNameContains(String name);
    @Query("SELECT p FROM Product p WHERE discontinued = :discontinued")
    List<Product> findByDiscontinued(@Param("discontinued") int discontinued);
    List<Product> findByUnitPriceGreaterThanEqual(BigDecimal price);
    List<Product> findByCategoryCategoryName(String name);

    @Query("SELECT p FROM Product p JOIN p.category c WHERE c.categoryName = :name")
    List<Product> findByCategoryName(@Param("name") String name);

    //metodo che per ogni nome di categoria di ogni product mi dà il nome delle categorie
    // e il numero di prodotti che ci sono in ogni categoria
    @Query("SELECT p.category.categoryName, COUNT(p) FROM Product p GROUP BY p.category.categoryName")
    List<Object[]> findByCategoryNameAndProductCount(@Param("categoryname") String categoryName);

    @Query("SELECT new org.generation.italy.springdemo.models.dtos.ProductSummary(p.productName, p.unitPrice) FROM Product p")
    List<ProductSummary> getProductSummaries();

    //tutti i prodotti non ordinati
    @Query("""
            SELECT p 
            FROM Product p 
            WHERE p.productId NOT IN (
                  SELECT od.product.productId 
                  FROM OrderDetails od
                  ) 
            """)
    List<Product> findNeverOrdered();


    //cancellare i prodotti che costano meno di una certa quantità
    @Modifying
    @Query("UPDATE Product p SET p.discontinued = 1 WHERE p.unitPrice < :amount")
    int discontinueProductsUnder(@Param("amount") BigDecimal amount);


}

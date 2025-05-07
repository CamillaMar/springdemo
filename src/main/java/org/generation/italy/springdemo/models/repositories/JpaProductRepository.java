package org.generation.italy.springdemo.models.repositories;

import org.generation.italy.springdemo.models.dtos.ProductSummary;
import org.generation.italy.springdemo.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface JpaProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByProductNameContains(String name);
    @Query("SELECT p FROM Product p WHERE discontinued = :discontinued")
    List<Product> findByDiscontinued(@Param("discontinued") int discontinued);
    List<Product> findByUnitPriceGreaterThanEqual(BigDecimal price);
    List<Product> findByCategoryCategoryName(String name);
    //Tutti i prodotti per cui il nome della loro categoria è un certo nome che do in input
    @Query("SELECT p FROM Product p JOIN p.category c WHERE c.categoryName = :category ")
    List<Product> findByCategoryName(@Param("name") String name);
    //metodo che per ogni categoria di prodotto mi da il nome delle categorie e il numero di prodotti che ci sono in ogni categoria
    @Query("SELECT p.category.categoryname, COUNT(p) FROM Product p GROUP BY p.category.categoryName")
    List<Object[]> findByCategoryNameAndProductCount(@Param("categoryname") String categoryName);

    @Query("SELECT new org.generation.italy.springdemo.models.dtos.ProductSummary(p.productName, p.unitPrice) FROM Product p")
    List<ProductSummary> getProductsSummary();

    //tutti i prodotti non ordinati
    @Query("""
            SELECT p
            FROM Product p
            WHERE p.productId NOT IN (
            SELECT od.product.productid
            FROM orderDetails od
            )"""
    )
    List<Product> findNeverOrdered();
    //cancellare i prodotti che costano meno di una certa cifra
    @Modifying
    @Query("UPDATE product p SET p.discontinued = 1 WHERE p.unitPrice < :amount")
    int discontinueProductsUnder(@Param("amount") BigDecimal amount);

    //cancella i cust di una certa regione
    @Modifying
    @Query("DELETE FROM Customer c WHERE c.region = :region")
    int deleteCustomerFromRegion(@Param("region") String region);

    //cancella i customer con una regione nulla oltre a quelli di una certa regione
    @Modifying
    @Query("DELETE FROM Customer c WHERE c.region IS NULL OR = :region")
    int deleteCustomerFromRegionAndNull(@Param("region") String region);
}

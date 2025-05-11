package org.generation.italy.springdemo.models.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.generation.italy.springdemo.models.entities.Category;
import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.entities.Supplier;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomProductRepositoryImplementation implements CustomProductRepository{
    EntityManager entityManager;

    @Autowired
    public CustomProductRepositoryImplementation(EntityManager entityManager) {
        this.entityManager=entityManager;
    }

    @Override
    public List<Product> searchAllProducts(Integer supplierId, Integer categoryId, BigDecimal minPrice, BigDecimal maxPrice) throws DataException {
        //criteria builder consente di creare una criteria query
        CriteriaBuilder criteriaBuilder= entityManager.getCriteriaBuilder();

        //criteria query è una query custom con dei criteri personalizzabili
        CriteriaQuery<Product> productCriteriaQuery=criteriaBuilder.createQuery(Product.class);

        //root = la tabella di partenza della query,sarà il risultanto se non ci dovesse essere nessun parametro
        //in questo caso tutti i prodotti
        //equivalente a scrivere SELECT p FROM Product p
        Root<Product> productRoot= productCriteriaQuery.from(Product.class);

        //predicate sono condizioni che useremo per filtrare la query
        List<Predicate> predicates=new ArrayList<>();

        if(supplierId!=null){
            Join<Product, Supplier> supplierJoin=productRoot.join("supplier");
            Predicate supplierPredicate=criteriaBuilder.equal(supplierJoin.get("supplierId"), supplierId);
            predicates.add(supplierPredicate);
        }

        if(categoryId!=null){
            Join<Product, Category> categoryJoin=productRoot.join("category");
            Predicate categoryPredicate=criteriaBuilder.equal(categoryJoin.get("categoryId"), categoryId);
            predicates.add(categoryPredicate);
        }

        if(minPrice!=null){
            Predicate minPricePredicate = criteriaBuilder.greaterThanOrEqualTo(productRoot.get("cost"), minPrice);
            predicates.add(minPricePredicate);
        }

        if(maxPrice!=null){
            Predicate maxPricePredicate=criteriaBuilder.lessThanOrEqualTo(productRoot.get("cost"), maxPrice);
            predicates.add(maxPricePredicate);
        }

        productCriteriaQuery.select(productRoot).where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(productCriteriaQuery).getResultList();
    }
}

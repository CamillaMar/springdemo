package org.generation.italy.springdemo.models.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.generation.italy.springdemo.models.entities.Customer;
import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.searchcriteria.CustomerFilterCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class JpaCustomerRepositoryCustomImpl implements JpaCustomerRepositoryCustom {
    EntityManager entityManager;

    @Autowired
    public JpaCustomerRepositoryCustomImpl( EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Customer> searchCustomers(CustomerFilterCriteria filters) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> query = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> root = query.from(Customer.class);

        List<Predicate> queryFilters = new ArrayList<>();

        if(filters.getCustId() != null){
            queryFilters.add(criteriaBuilder.equal(root.get("custId"), filters.getCustId()));
        }
        if(filters.getCompanyName() != null){
            queryFilters.add(criteriaBuilder.equal(root.get("companyName"), filters.getCompanyName()));
        }
        if(filters.getContactName() != null){
            queryFilters.add(criteriaBuilder.equal(root.get("contactName"), filters.getContactName()));
        }
        if(filters.getContactTitle() != null){
            queryFilters.add(criteriaBuilder.equal(root.get("contactTitle"), filters.getContactTitle()));
        }
        if(filters.getCity() != null){
            queryFilters.add(criteriaBuilder.equal(root.get("city"), filters.getCity()));
        }
        if(filters.getRegion() != null){
            queryFilters.add(criteriaBuilder.equal(root.get("region"), filters.getRegion()));
        }
        if(filters.getCountry() != null) {
            queryFilters.add(criteriaBuilder.equal(root.get("country"), filters.getCountry()));
        }

        query.select(root).where(criteriaBuilder.and(queryFilters.toArray(new Predicate[0])));

        return entityManager.createQuery(query).getResultList();
    }
}

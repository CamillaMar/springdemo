package org.generation.italy.springdemo.models.repositories;

import org.generation.italy.springdemo.models.entities.Customer;
import org.generation.italy.springdemo.models.entities.Shipper;
import org.generation.italy.springdemo.models.searchcriteria.CustomerFilterCriteria;

import java.util.List;

public interface JpaCustomerRepositoryCustom {
    public List<Customer> searchCustomers(CustomerFilterCriteria filter);
}

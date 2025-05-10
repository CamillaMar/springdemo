package org.generation.italy.springdemo.models.services;

import org.generation.italy.springdemo.models.entities.*;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Profile("jdbc")
public class JdbcService implements StoreService{
    @Override
    public Optional<Product> findProductById(int id) throws DataException {
        return Optional.empty();
    }

    @Override
    public Optional<Category> findCategoryById(int id) throws DataException {
        return Optional.empty();
    }

    @Override
    public List<Product> findByProductNameContains(String name) throws DataException {
        return List.of();
    }

    @Override
    public List<Product> findProductsByDiscontinued(int discontinued) throws DataException {
        return List.of();
    }

    @Override
    public List<Product> findAllProducts() throws DataException {
        return List.of();
    }

    @Override
    public Product saveProduct(Product p, int supplierId, int categoryId) throws DataException {
        return null;
    }

    @Override
    public List<Category> findAllCategories() {
        return List.of();
    }

    @Override
    public List<Supplier> findAllSuppliers() {
        return List.of();
    }

    @Override
    public List<Order> findOrdersByCustomerCustId(int custId) throws DataException{
        return List.of();
    }

    @Override
    public List<Customer> findAllCustomers() {
        return List.of();
    }

    @Override
    public void deleteOrder(int orderId) {
    }

    @Override
    public List<Order> findAllOrders() {
        return List.of();
    }

    @Override
    public boolean deleteProduct(int id) throws DataException {
        return false;
    }

    @Override
    public boolean updateProduct(Product p, Product np) throws DataException {
        return false;
    }
}

package org.generation.italy.springdemo.models.services;

import org.generation.italy.springdemo.models.entities.*;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.exceptions.EntityNotFoundException;
import org.generation.italy.springdemo.models.searchcriteria.ProductFilterCriteria;

import java.util.List;
import java.util.Optional;

public interface StoreService {
    // Product
    Optional<Product> findProductById(int id) throws DataException;
    List<Product> findByProductNameContains(String name) throws DataException;
    List<Product> findProductsByDiscontinued(int discontinued) throws DataException;
    List<Product> findAllProducts() throws DataException;
    Product saveProduct(Product p, int supplierId, int categoryId) throws DataException, EntityNotFoundException;
    boolean deleteProductById(int id) throws DataException;
    boolean updateProduct(Product p, int supplierId, int categoryId) throws DataException, EntityNotFoundException;
    List<Product> searchProducts(ProductFilterCriteria filters) throws DataException;
    List<Product> findMostExpensiveProducts(Integer numberOfProducts) throws DataException;

    // Order
    List<Order> findAllOrdersByCustomerId(int custId) throws DataException;
    boolean deleteOrderById(int orderId) throws DataException;
    void deleteOrderDetailsByOrderId(int orderId) throws DataException;
    Order findOrderById(int id) throws DataException;

    // Category
    List<Category> findAllCategories() throws DataException;
    Optional<Category> findCategoryById(int id) throws DataException;

    // Employee
    List<Employee> findAllEmployeesWithMostOrders() throws DataException;
    List<Employee> findAllEmployees() throws DataException;
    Optional<Employee> findEmployeeById(int id) throws DataException;
    boolean updateEmployee(Employee e, int managerId) throws DataException, EntityNotFoundException;

    // Customer
    List<Customer> findAllCustomersWithOrders() throws DataException;
    List<Customer> findAllCustomers() throws DataException;
    List<Customer> findAllCustomersWithMostOrders() throws DataException;
}

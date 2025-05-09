package org.generation.italy.springdemo.models.services;

import org.generation.italy.springdemo.models.entities.*;
import org.generation.italy.springdemo.models.exceptions.DataException;

import java.util.List;
import java.util.Optional;

public interface StoreService {
    Optional<Product> findProductById (int id) throws DataException;
    Optional<Category> findCategoryById(int id) throws DataException;
    List<Product> findByProductNameContains(String name) throws DataException;
    List<Product> findProductsByDiscontinued(int discontinued) throws DataException;
    List<Product> findAllProducts() throws DataException;
    Product saveProduct(Product p, int supplierId, int categoryId) throws DataException;
    List<Category> findAllCategories();
    List<Supplier> findAllSuppliers();
    List<Order> findOrdersByCustId(int custId) throws DataException;
    List<Order> findAllOrders();
    void deleteOrderById(int orderId) throws DataException;
    List<OrderDetails> findOrderDetailsByOrderId(int orderId) throws DataException;
    void deleteOrderOrderDetails(int orderId) throws DataException;
    List<Customer> findAllCustomers();
}

package org.generation.italy.springdemo.models.services;

import org.generation.italy.springdemo.models.entities.*;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.exceptions.EntityNotFoundException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface StoreService {
    Optional<Product> findProductById(int id) throws DataException;
    Optional<Category> findCategoryById(int id) throws DataException;
    List<Product> findByProductNameContains(String name) throws DataException;
    List<Product> findProductsByDiscontinued(int discontinued) throws DataException;
    List<Product> findAllProducts() throws DataException;
    Product saveProduct(Product p, int supplierId, int categoryId) throws DataException, EntityNotFoundException;
    List<Category> findAllCategories() throws DataException;
    List<Supplier> findAllSuppliers() throws DataException;
    List<Order> findOrdersByCustId(int custId) throws DataException;
    List<Order> findAllOrders() throws DataException;
    void deleteOrderById(int orderId) throws DataException;
    List<OrderDetails> findOrderDetailsByOrderId(int orderId) throws DataException;
    void deleteOrderOrderDetails(int orderId) throws DataException;
    List<Customer> findAllCustomers() throws DataException;
    boolean deleteProduct(int id) throws DataException;
    boolean updateProduct(Product p, int supplierId, int categoryId) throws DataException, EntityNotFoundException;
    List<Product> searchProducts(Integer supplierId, Integer categoryId, BigDecimal minPrice, BigDecimal maxPrice, String namePart) throws DataException;
}
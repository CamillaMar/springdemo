package org.generation.italy.springdemo.models.services;

import org.generation.italy.springdemo.models.entities.*;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.exceptions.EntityNotFoundException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface StoreService {
    List<Product> searchProducts(Integer categoryId, Integer supplierId, BigDecimal minPrice, BigDecimal maxPrice) throws DataException;
    Optional<Product> findProductById (int id) throws DataException;
    Optional<Category> findCategoryById(int id) throws DataException;
    List<Product> findByProductNameContains(String name) throws DataException;
    List<Product> findProductsByDiscontinued(int discontinued) throws DataException;
    List<Product> findAllProducts() throws DataException;
    Product saveProduct(Product p, int supplierId, int categoryId) throws DataException;
    List<Category> findAllCategories();
    List<Supplier> findAllSuppliers();
    List<Order> findOrdersByCustomerCustId(int customerId) throws DataException;
    List<Customer> findAllCustomers();
    void deleteOrder(int orderId);
    List<Order> findAllOrders();
    boolean deleteProduct(int id) throws DataException;
    Product updateProduct(int id, Product np, int supplierId, int categoryId) throws DataException, EntityNotFoundException;
    public void setSupplierAndCategory(Product p, int supplierId, int categoryId) throws DataException, EntityNotFoundException;
}

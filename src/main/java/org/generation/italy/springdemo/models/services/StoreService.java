package org.generation.italy.springdemo.models.services;

import org.generation.italy.springdemo.models.entities.Category;
import org.generation.italy.springdemo.models.entities.Customer;
import org.generation.italy.springdemo.models.entities.Order;
import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.exceptions.EntityNotFoundException;
import org.generation.italy.springdemo.restdtos.ProductRestDto;

import java.util.List;
import java.util.Optional;

public interface StoreService {
    Optional<Product> findProductById(int id) throws DataException;
    Optional<Category> findCategoryById(int id) throws DataException;
    List<Product> findByProductNameContains(String name) throws DataException;
    List<Product> findProductsByDiscontinued(int discontinued) throws DataException;
    List<Product> findAllProducts() throws DataException;
    Product saveProduct(Product p, int supplierId, int categoryId) throws DataException, EntityNotFoundException;
    List<Customer> findAllCustomersWithOrders() throws DataException;
    List<Order> findAllOrdersByCustomerId(int custId) throws DataException;
    boolean deleteOrderById(int orderId) throws DataException;
    void deleteOrderDetailsByOrderId(int orderId) throws DataException;
    Order findOrderById(int id) throws DataException;
    boolean deleteProductById(int id) throws DataException;
    Product updateProduct(Product p, ProductRestDto dto) throws DataException, EntityNotFoundException;
}

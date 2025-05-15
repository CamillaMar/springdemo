package org.generation.italy.springdemo.models.services;

import org.generation.italy.springdemo.models.entities.*;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.exceptions.EntityNotFoundException;
import org.generation.italy.springdemo.models.searchCriteria.OrderFilterCriteria;
import org.generation.italy.springdemo.models.searchCriteria.ProductFilterCriteria;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Profile ("jdbc")
public class JdbcService implements StoreService {
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
    public List<Order> findOrdersByCustId(int custId) throws DataException {
        return List.of();
    }

    @Override
    public List<Order> findAllOrders() {
        return List.of();
    }

    @Override
    public void deleteOrderById(int orderId) throws DataException {

    }

    @Override
    public List<OrderDetails> findOrderDetailsByOrderId(int orderId) throws DataException {
        return List.of();
    }

    @Override
    public void deleteOrderOrderDetails(int orderId) throws DataException {

    }

    @Override
    public List<Customer> findAllCustomers() {
        return List.of();
    }

    @Override
    public boolean deleteProduct(int id) throws DataException {
        return false;
    }

    @Override
    public boolean updateProduct(Product p, int supplierId, int categoryId) throws DataException, EntityNotFoundException {
        return false;
    }

    @Override
    public List<Product> searchProducts(ProductFilterCriteria filters) throws DataException {
        return List.of();
    }

    @Override
    public List<Employee> searchEmployee() throws DataException {
        return List.of();
    }

    @Override
    public List<Order> searchOrders(OrderFilterCriteria ofc) throws DataException {
        return List.of();
    }

    @Override
    public List<Customer> searchCustomer() throws DataException {
        return List.of();
    }

    @Override
    public List<Customer> findCustomerByOrderNum(Integer limite) throws DataException {
        return List.of();
    }

    @Override
    public List<Employee> findEmployeeByOrderNum(Integer limite) throws DataException {
        return List.of();
    }
}

package org.generation.italy.springdemo.models.services;

import org.generation.italy.springdemo.models.dtos.SelectListElement;
import org.generation.italy.springdemo.models.entities.*;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.searchcriteria.ProductFilterCriteria;
import org.generation.italy.springdemo.models.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface StoreService {
    Optional<Product> findProductById (int id) throws DataException;
    Optional<Category> findCategoryById(int id) throws DataException;
    List<Product> findByProductNameContains(String name) throws DataException;
    List<Product> findProductsByDiscontinued(int discontinued) throws DataException;
    List<Product> findAllProducts() throws DataException;
    Product saveProduct(Product p, int supplierId, int categoryId) throws DataException, EntityNotFoundException;
    List<Category> findAllCategories() throws DataException;
    List<Supplier> findAllSuppliers() throws DataException;
    boolean deleteProduct(int id) throws DataException;
    List<SelectListElement> getSelectListCustomers();
    List<Order> findOrdersByCustomer(Integer CustId);
    void deleteOrder(Integer orderId);
    boolean updateProduct(Product newProduct, int categoryId, int supplierId) throws DataException, EntityNotFoundException;
    List<Product> searchProduct(ProductFilterCriteria filters) throws DataException;
    List<Product> findMostExpensiveProducts(Integer topN) throws DataException;
    Optional<Customer> findCustomerById(int id) throws DataException;

    Optional<Customer> findCustomerByMostOrders() throws DataException;

    Optional<Employee> findEmployeeByMostOrders() throws DataException;

    Optional<Employee> findEmployeeById(int empId) throws DataException;

    boolean updateEmployee(Employee e, int managerId, List<Order> orders) throws DataException, EntityNotFoundException;
}

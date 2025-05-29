package org.generation.italy.springdemo.models.services;

import org.generation.italy.springdemo.models.dtos.SelectListElement;
import org.generation.italy.springdemo.models.entities.*;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.exceptions.EntityNotFoundException;
import org.generation.italy.springdemo.models.searchcriteria.ProductFilterCriteria;
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
    public List<SelectListElement> getSelectListCustomers() {
        return List.of();
    }

    @Override
    public List<Order> findOrdersByCustomer(Integer custId) {
        return List.of();
    }

    @Override
    public void deleteOrder(Integer orderId) {
    }

    @Override
    public boolean updateProduct(Product newProduct, int categoryId, int supplierId) throws DataException, EntityNotFoundException {
        return false;
    }

    @Override
    public List<Product> searchProduct(ProductFilterCriteria filters) {
        return List.of();
    }

    @Override
    public List<Product> findMostExpensiveProducts(Integer topN) {
        return List.of();
    }

    @Override
    public Optional<Customer> findCustomerById(int id) throws DataException {
        return Optional.empty();
    }

    @Override
    public Optional<Customer> findCustomerByMostOrders() {
        return Optional.empty();
    }

    @Override
    public Optional<Employee> findEmployeeByMostOrders() {
        return Optional.empty();
    }

    @Override
    public Optional<Employee> findEmployeeById(int empId) {
        return Optional.empty();
    }

    @Override
    public boolean updateEmployee(Employee e, int managerId, List<Order> orders) {
        return false;
    }

    @Override
    public Optional<Todo> findTodoById(int id) throws DataException {
        return Optional.empty();
    }

    @Override
    public List<Todo> findAllTodos() throws DataException {
        return List.of();
    }

    @Override
    public boolean deleteTodo(int id) throws DataException, EntityNotFoundException {
        return false;
    }

    @Override
    public Todo saveTodo(Todo t) throws DataException {
        return null;
    }

    @Override
    public boolean updateTodo(Todo todo) throws DataException, EntityNotFoundException {
        return false;
    }

    @Override
    public boolean deleteProduct(int id) throws DataException {
        return false;
    }
}

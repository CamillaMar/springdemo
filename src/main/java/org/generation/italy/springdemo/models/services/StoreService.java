package org.generation.italy.springdemo.models.services;

import org.generation.italy.springdemo.models.entities.*;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.searchcriteria.CustomerFilterCriteria;
import org.generation.italy.springdemo.restdtos.ProductRestDto;

import java.math.BigDecimal;
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
    List<Customer> findAllCustomers();
    List<Order> findByCustId(int custId);
    void deleteOrderById(Integer id);
    Optional<Order> findOrderById (int id) ;
    boolean deleteProduct(int id) throws DataException;
    Product updateProduct(Product p, int supplierID, int categoryId) throws DataException;
    List<Product> searchProducts(Integer categoryId, Integer supplierId, BigDecimal minPrice, BigDecimal maxPrice) throws DataException;
    List<Student> findAllStudents() throws DataException;
    Optional<Student> findStudentById(int id) throws DataException;
    boolean deleteStudent(int id) throws DataException;

    Student updateStudent(Student st);

    void saveStudent(Student s);

    Object searchCustomers(CustomerFilterCriteria filters);

    List<Employee> findAllEmployees();
}

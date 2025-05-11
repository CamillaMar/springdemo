package org.generation.italy.springdemo.models.services;

import org.generation.italy.springdemo.models.entities.Category;
import org.generation.italy.springdemo.models.entities.Employee;
import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.entities.Supplier;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.restdtos.EmployeeRestDto;
import org.generation.italy.springdemo.viewmodels.OrderViewModel;

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
    List<OrderViewModel> findAllOrders();
    List<Employee> findAllEmployee();
    Employee findEmployeeById(int id);
    Employee saveEmployee(Employee e);
    Employee updateEmployee(EmployeeRestDto dto);

    void deleteEmployee(int id) throws DataException;
}

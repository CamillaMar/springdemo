package org.generation.italy.springdemo.models.services;

import org.generation.italy.springdemo.models.dtos.SelectListElement;
import org.generation.italy.springdemo.models.entities.Category;
import org.generation.italy.springdemo.models.entities.Order;
import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.entities.Supplier;
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
    List<Product> findMostExpensiveProducts(Integer topN);
    List<Product> findByCategory(int categoryId);
}

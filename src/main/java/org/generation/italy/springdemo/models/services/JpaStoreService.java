package org.generation.italy.springdemo.models.services;

import jakarta.persistence.PersistenceException;
import org.generation.italy.springdemo.models.dtos.SelectListElement;
import org.generation.italy.springdemo.models.entities.Category;
import org.generation.italy.springdemo.models.entities.Order;
import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.entities.Supplier;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.exceptions.EntityNotFoundException;
import org.generation.italy.springdemo.models.repositories.*;
import org.generation.italy.springdemo.models.searchcriteria.ProductFilterCriteria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@Profile("jpa")
public class JpaStoreService implements StoreService{
    private JpaProductRepository productRepo;
    private JpaCategoryRepository categoryRepo;
    private JpaSupplierRepository supplierRepo;
    private JpaCustomerRepository customerRepo;
    private JpaOrderRepository orderRepo;
    private JpaOrderDetailsRepository orderDetailsRepo;

    @Autowired
    public JpaStoreService(JpaProductRepository productRepo, JpaCategoryRepository categoryRepo, JpaSupplierRepository supplierRepo, JpaCustomerRepository customerRepo, JpaOrderRepository orderRepo, JpaOrderDetailsRepository orderDetailsRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.supplierRepo = supplierRepo;
        this.customerRepo = customerRepo;
        this.orderRepo = orderRepo;
        this.orderDetailsRepo = orderDetailsRepo;
    }

    @Override
    public Optional<Product> findProductById(int id) throws DataException {
        return productRepo.findById(id);
    }

    @Override
    public Optional<Category> findCategoryById(int id) throws DataException {
        return categoryRepo.findById(id);
    }

    @Override
    public List<Product> findByProductNameContains(String name) throws DataException {
        try{
            return productRepo.findByProductNameContains(name);
        }catch(PersistenceException pe) {
            throw new DataException(pe.getMessage(), pe);
        }
    }

    @Override
    public List<Product> findProductsByDiscontinued(int discontinued) throws DataException {
        try{
            return productRepo.findByDiscontinued(discontinued);
        }catch(PersistenceException pe) {
            throw new DataException(pe.getMessage(), pe);
        }
    }

    @Override
    public List<Product> findAllProducts() throws DataException {
        return productRepo.findAll();
    }

    @Override
    @Transactional
    public Product saveProduct(Product p, int supplierId, int categoryId) throws DataException, EntityNotFoundException {
        try {
            Optional<Supplier> os = supplierRepo.findById(supplierId);
            if(os.isEmpty()){
                throw new EntityNotFoundException(Supplier.class, supplierId);
            }
            Supplier s = os.get();
            Optional<Category> oc = categoryRepo.findById(categoryId);
            Category c = oc.orElseThrow(()-> new EntityNotFoundException(Category.class, categoryId));
            p.setSupplier(s);
            p.setCategory(c);
            productRepo.save(p);
            return p;
        } catch (PersistenceException pe) {
            throw new DataException("Errore nella creazione di un nuovo prodotto", pe);
        }
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public List<Supplier> findAllSuppliers() {
        return supplierRepo.findAll();
    }

    @Override
    public List<SelectListElement> getSelectListCustomers() {
        return customerRepo.getSelectListCustomers();
    }

    @Override
    public List<Order> findOrdersByCustomer(Integer custId) {
        List<Order> ordersBy = orderRepo.findByCustomerCustId(custId);
        return ordersBy;
    }

    @Override
    @Transactional
    public void deleteOrder(Integer orderId) {
        orderDetailsRepo.deleteOrderDetailsByOrderId(orderId);
        orderRepo.deleteById(orderId);
    }

    @Override
    @Transactional
    public boolean updateProduct(Product newProduct, int categoryId, int supplierId) throws DataException, EntityNotFoundException {
        try {
            Optional<Product> op = productRepo.findById(newProduct.getProductId());
            if(op.isEmpty()){
                return false;
            }

            Supplier s = supplierRepo.findById(supplierId).orElseThrow(()-> new EntityNotFoundException(Supplier.class, supplierId));
            Category c = categoryRepo.findById(categoryId).orElseThrow(()-> new EntityNotFoundException(Category.class, categoryId));

            newProduct.setSupplier(s);
            newProduct.setCategory(c);

            productRepo.save(newProduct);

            return true;
        } catch (PersistenceException pe) {
            throw new DataException("Errore nella modifica di un prodotto", pe);
        }
    }

    @Override
    public List<Product> searchProduct(ProductFilterCriteria filters) throws DataException{
        try{
            return productRepo.searchProducts(filters);
        }catch(PersistenceException pe){
            throw new DataException("Errore nella ricerca del prodotto", pe);
        }
    }

    @Override
    public List<Product> findProductOrderedByCostDesc(Integer topN) throws DataException {
        return productRepo.findByOrderByCostDesc(Limit.of(topN));
    }


    @Override
    public List<Product> findCategoryCategoryName(String categoryName) throws DataException {
        return productRepo.findByCategoryCategoryName(categoryName);
    }

    @Override
    @Transactional
    public boolean deleteProduct(int id) throws DataException {
        Optional<Product> op = productRepo.findById(id);
        if(op.isPresent()) {
            productRepo.delete(op.get());
            return true;
        }
        return false;
    }
}

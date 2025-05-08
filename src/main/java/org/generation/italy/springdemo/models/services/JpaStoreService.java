package org.generation.italy.springdemo.models.services;

import jakarta.persistence.PersistenceException;
import org.generation.italy.springdemo.models.dtos.SelectListElement;
import org.generation.italy.springdemo.models.entities.Category;
import org.generation.italy.springdemo.models.entities.Order;
import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.entities.Supplier;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.repositories.*;
import org.generation.italy.springdemo.viewmodels.OrderViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

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

    @Autowired
    public JpaStoreService(JpaProductRepository productRepo, JpaCategoryRepository categoryRepo, JpaSupplierRepository supplierRepo, JpaCustomerRepository customerRepo, JpaOrderRepository orderRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.supplierRepo = supplierRepo;
        this.customerRepo = customerRepo;
        this.orderRepo = orderRepo;
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
            return  productRepo.findByProductNameContains(name);
        }catch(PersistenceException pe) {
            throw new DataException(pe.getMessage(), pe);
        }
    }

    @Override
    public List<Product> findProductsByDiscontinued(int discontinued) throws DataException {
        try{
            return  productRepo.findByDiscontinued(discontinued);
        }catch(PersistenceException pe) {
            throw new DataException(pe.getMessage(), pe);
        }
    }

    @Override
    public List<Product> findAllProducts() throws DataException {
        return productRepo.findAll();
    }

    @Override
    public Product saveProduct(Product p, int supplierId, int categoryId) throws DataException {
        Optional<Supplier> os = supplierRepo.findById(supplierId);
        if(os.isEmpty()){
            throw new DataException(String.format("Il supplier con id %d non esiste", supplierId));
        }
        Supplier s = os.get();
        Optional<Category> oc = categoryRepo.findById(categoryId);
        Category c = oc.orElseThrow(()-> new DataException(String.format("la categoria con id %d non esiste", categoryId)));
        p.setSupplier(s);
        p.setCategory(c);
        productRepo.save(p);
        return p;
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
}

package org.generation.italy.springdemo.models.services;

import jakarta.persistence.PersistenceException;
import org.generation.italy.springdemo.models.entities.*;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.exceptions.EntityNotFoundException;
import org.generation.italy.springdemo.models.repositories.*;
import org.generation.italy.springdemo.models.repositories.criteriaRepositories.CriteriaProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Profile("jpa")
public class JpaStoreService implements StoreService{
    private JpaProductRepository productRepo;
    private JpaCategoryRepository categoryRepo;
    private JpaSupplierRepository supplierRepo;
    private JpaOrderRepository orderRepo;
    private JpaCustomerRepository customerRepo;
    private CriteriaProductRepository criteriaProductRepo;


    @Autowired
    public JpaStoreService(JpaProductRepository productRepo,
                           JpaCategoryRepository categoryRepo,
                           JpaSupplierRepository supplierRepo,
                           JpaOrderRepository orderRepo,
                           JpaCustomerRepository customerRepo,
                           CriteriaProductRepository criteriaProductRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.supplierRepo = supplierRepo;
        this.orderRepo = orderRepo;
        this.customerRepo = customerRepo;
        this.criteriaProductRepo = criteriaProductRepo;
    }

    @Override
    public List<Product> searchProducts(Integer categoryId, Integer supplierId, BigDecimal minPrice, BigDecimal maxPrice) throws DataException {
        return criteriaProductRepo.searchProducts(categoryId, supplierId, minPrice, maxPrice);
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
    public List<Order> findOrdersByCustomerCustId(int custId) throws DataException {
        return orderRepo.findByCustomerCustId(custId);
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public void deleteOrder(int orderId) {
        orderRepo.deleteById(orderId);
    }

    @Override
    public List<Order> findAllOrders() {
        return orderRepo.findAll();
    }

    @Override
    @Transactional
    public boolean deleteProduct(int id) throws DataException {
        Optional<Product> op = productRepo.findById(id);
        if(op.isEmpty()) {
            return false;
        }
        productRepo.delete(op.get());
        return true;
    }

    @Transactional
    @Override
    public Product updateProduct(int id, Product np, int supplierId, int categoryId) throws DataException, EntityNotFoundException {
        Optional<Product> op = productRepo.findById(id);
        if(op.isEmpty()){
            throw new EntityNotFoundException(Product.class, id);
        }
        Product p = op.get();
        p.setProductName(np.getProductName());
        p.setCost(np.getCost());
        p.setDiscontinued(np.isDiscontinued());
        setSupplierAndCategory(p, supplierId, categoryId);
        productRepo.save(p);
        return p;

    }

    @Override
    public void setSupplierAndCategory(Product p, int supplierId, int categoryId) throws DataException, EntityNotFoundException {
        Optional<Supplier> os = supplierRepo.findById(supplierId);
        Supplier s = os.orElseThrow(()-> new EntityNotFoundException(Supplier.class, supplierId));
        Optional<Category> oc = categoryRepo.findById(categoryId);
        Category c = oc.orElseThrow(()-> new EntityNotFoundException(Category.class, categoryId));
        p.setSupplier(s);
        p.setCategory(c);
    }
}

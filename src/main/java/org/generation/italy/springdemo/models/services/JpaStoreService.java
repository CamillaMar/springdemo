package org.generation.italy.springdemo.models.services;

import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import org.generation.italy.springdemo.models.entities.*;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.exceptions.EntityNotFoundException;
import org.generation.italy.springdemo.models.repositories.*;
import org.generation.italy.springdemo.models.repositories.JpaOrderDetailsRepository;
import org.generation.italy.springdemo.models.searchCriteria.OrderFilterCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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
    private JpaOrderDetailsRepository orderDetailsRepo;
    private JpaCustomerRepository customerRepo;

    @Autowired
    public JpaStoreService(JpaProductRepository productRepo, JpaCategoryRepository categoryRepo, JpaSupplierRepository supplierRepo,
                           JpaOrderRepository orderRepo, JpaOrderDetailsRepository orderDetailsRepo, JpaCustomerRepository customerRepo){
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.supplierRepo = supplierRepo;
        this.orderRepo = orderRepo;
        this.orderDetailsRepo = orderDetailsRepo;
        this.customerRepo = customerRepo;
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
        try {
            return productRepo.findByProductNameContains(name);
        } catch (PersistenceException pe) {
            throw new DataException(pe.getMessage(), pe);
        }
    }

    @Override
    public List<Product> findProductsByDiscontinued(int discontinued) throws DataException {
        try {
            return productRepo.findByDiscontinued(discontinued);
        } catch (PersistenceException pe) {
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
            throw new DataException("errore nella creazione di un nuovo prodotto", pe);
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
    public List<Order> findOrdersByCustId(int custId) throws DataException{
        return orderRepo.findByCustomerCustId(custId);
    }

    @Override
    public List<Order> findAllOrders() {
        return orderRepo.findAll();
    }

    @Override
    public void deleteOrderById(int orderId) throws DataException {
        Optional<Order> oo = orderRepo.findById(orderId);
        Order o = oo.orElseThrow(() -> new DataException(String.format("L'order con ID %d non esiste", orderId)));
        orderRepo.deleteById(orderId);
    }

    @Override
    public List<OrderDetails> findOrderDetailsByOrderId(int orderId) throws DataException {
        return orderDetailsRepo.findByOrderOrderId(orderId);
    }

    @Override
    public void deleteOrderOrderDetails(int orderId) throws DataException {
        Optional<Order> oo = orderRepo.findById(orderId);
        Order o = oo.orElseThrow(()-> new DataException(String.format("L'ordine con Id %d non esiste!", orderId )));
        List<OrderDetails> orderDetails = o.getOrderDetails();
        for(OrderDetails od : orderDetails){
            orderDetailsRepo.delete(od);
        }
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    @Transactional
    public boolean deleteProduct(int id) throws DataException {
        Optional<Product> op = productRepo.findById(id);
        if(op.isPresent()){
            productRepo.delete(op.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean updateProduct(Product p, int supplierId, int categoryId) throws DataException, EntityNotFoundException {
        try {
            Optional<Product> op = productRepo.findById(p.getProductId());
            if(op.isEmpty()){
                return false;
            }

            Optional<Supplier> os = supplierRepo.findById(supplierId);
            Supplier s = os.orElseThrow(()->new EntityNotFoundException(Supplier.class, supplierId));
            Optional<Category> oc = categoryRepo.findById(categoryId);
            Category c = oc.orElseThrow(()-> new EntityNotFoundException(Category.class, categoryId));
            p.setSupplier(s);
            p.setCategory(c);
            productRepo.save(p);

            return true;
        } catch (PersistenceException pe) {
            throw new DataException("errore nella modifica di un prodotto", pe);
        }
    }

    @Override
    public List<Product> searchProducts(Integer supplierId, Integer categoryId, BigDecimal minPrice, BigDecimal maxPrice, String namePart) throws DataException {
        try{
            return productRepo.searchProductsFilters(supplierId, categoryId, minPrice, maxPrice, namePart);
        } catch (PersistenceException pe){
            throw new DataException("Errore nella ricerca die prodotti", pe);
        }
//        try {
//            return productRepo.findAll(
//                    Specification.where(ProductSpecifications.hasSupplierId(supplierId))
//                            .and(ProductSpecifications.hasCategoryId(categoryId))
//                            .and(ProductSpecifications.isMinPriceGreaterThan(minPrice))
//                            .and(ProductSpecifications.isMaxPriceLessThan(maxPrice))
//                            .and(ProductSpecifications.hasNameLike(namePart))
//            );
//        } catch (PersistenceException pe) {
//            throw new DataException("Errore nella ricerca die prodotti", pe);
//        }
   }

    @Override
    public List<Order> searchOrders(OrderFilterCriteria filters) {
        return orderRepo.searchOrdersFilters(filters);
    }

}
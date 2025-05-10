package org.generation.italy.springdemo.models.services;

import jakarta.persistence.PersistenceException;
import org.generation.italy.springdemo.models.entities.*;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.exceptions.EntityNotFoundException;
import org.generation.italy.springdemo.models.repositories.*;
import org.generation.italy.springdemo.models.repositories.criteria.ProductCriteriaRepository;
import org.generation.italy.springdemo.restdtos.ProductRestDto;
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
    private JpaCustomerRepository customerRepo;
    private JpaOrderRepository orderRepo;
    private JpaOrderDetailsRepository orderDetailsRepo;

    @Autowired
    public JpaStoreService(JpaProductRepository productRepo, JpaCategoryRepository categoryRepo, JpaSupplierRepository supplierRepo,
                           JpaCustomerRepository customerRepo, JpaOrderRepository orderRepo, JpaOrderDetailsRepository orderDetailsRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.supplierRepo = supplierRepo;
        this.customerRepo = customerRepo;
        this.orderRepo = orderRepo;
        this.orderDetailsRepo = orderDetailsRepo;
    }

    @Override
    public Optional<Product> findProductById(int id) throws DataException {
        try {
            return productRepo.findById(id);
        } catch (PersistenceException pe) {
            throw new DataException(String.format("Errore nella ricerca del prodotto con id %d", id), pe);
        }
    }

    @Override
    public Optional<Category> findCategoryById(int id) throws DataException {
        try {
            return categoryRepo.findById(id);
        } catch (PersistenceException pe) {
            throw new DataException(String.format("Errore nella ricerca della categoria con id %d", id), pe);
        }
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
    public List<Product> findAllProducts() throws DataException{
        try {
            return productRepo.findAll();
        } catch (PersistenceException pe) {
            throw new DataException("Errore nella ricerca di tutti i prodotti", pe);
        }
    }

    @Override
    @Transactional
    public Product saveProduct(Product p, int supplierId, int categoryId) throws DataException, EntityNotFoundException {
        try {
            Optional<Supplier> os = supplierRepo.findById(supplierId);
            Supplier s = os.orElseThrow(() -> new EntityNotFoundException(Supplier.class, supplierId));

            Optional<Category> oc = categoryRepo.findById(categoryId);
            Category c =  oc.orElseThrow(() -> new EntityNotFoundException(Category.class, categoryId));

            p.setSupplier(s);
            p.setCategory(c);
            productRepo.save(p);
            return p;
        } catch (PersistenceException pe) {
            throw new DataException("Errore nella creazione del prodotto", pe);
        }
    }

    @Override
    public List<Customer> findAllCustomersWithOrders() throws DataException {
        try {
            return customerRepo.findAllCustomersWithOrders();
        } catch (PersistenceException pe) {
            throw new DataException("Errore nella ricerca dei customers con degli ordini", pe);
        }
    }

    @Override
    public List<Order> findAllOrdersByCustomerId(int custId) throws DataException {
        try {
            return orderRepo.findAllByCustomerCustId(custId);
        } catch (PersistenceException pe) {
            throw new DataException(String.format("Errore nella ricerca degli ordini del customer con id %d", custId), pe);
        }
    }

    public boolean deleteOrderById(int orderId) throws DataException {
        try {
            Optional<Order> optionalOrder = orderRepo.findById(orderId);
            if(optionalOrder.isEmpty()) {
                return false;
            }

            deleteOrderDetailsByOrderId(orderId);
            orderRepo.deleteById(orderId);
            return true;
        } catch (PersistenceException pe) {
            throw new DataException(String.format("Errore nella cancellazione dell'ordine con id %d", orderId), pe);
        }
    }

    @Override
    public void deleteOrderDetailsByOrderId(int orderId) throws DataException {
        try {
            List<OrderDetails> odList = orderDetailsRepo.findAllByOrderOrderId(orderId);

            for (OrderDetails od : odList){
                orderDetailsRepo.delete(od);
            }
        } catch (PersistenceException pe) {
            throw new DataException(String.format("Errore nella cancellazione dei OrderDetails dell'ordine con id %d", orderId), pe);
        }
    }

    @Override
    public Order findOrderById(int id) throws DataException {
        try {
            Optional<Order> optionalOrder = orderRepo.findById(id);
            return optionalOrder.orElseThrow(() -> new DataException("Ordine con orderId: "+id+" non trovato"));
        } catch (PersistenceException pe) {
            throw new DataException(String.format("Errore nella ricerca dell'ordine con id %d", id), pe);
        }
    }

    @Override
    @Transactional
    public boolean deleteProductById(int id) throws DataException {
        try {
            Optional<Product> op = productRepo.findById(id);
            if(op.isEmpty()) {
                return false;
            }

            productRepo.delete(op.get());
            return true;
        } catch (PersistenceException pe) {
            throw new DataException(String.format("Errore nella cancellazione del prodotto con id %d", id), pe);
        }
    }

    @Override
    public Product updateProduct(Product p, ProductRestDto dto) throws DataException, EntityNotFoundException {
        try {
            p.setProductName(dto.getProductName());
            p.setUnitPrice(dto.getUnitPrice());
            p.setDiscontinued(dto.isDiscontinued() ? 1 : 0);
            return saveProduct(p, dto.getSupplierId(), dto.getCategoryId());
        } catch (PersistenceException pe) {
            throw new DataException(String.format("Errore nell'aggiornamento del prodotto con id %d", p.getProductId()), pe);
        }
    }

    @Override
    public List<Product> searchProducts(Integer categoryId, Integer supplierId, BigDecimal minPrice, BigDecimal maxPrice) throws DataException {
        try {
            return productRepo.searchProducts(categoryId, supplierId, minPrice, maxPrice);
        } catch (PersistenceException pe) {
            throw new DataException("Errore nella ricerca dei prodotti", pe);
        }
    }
}

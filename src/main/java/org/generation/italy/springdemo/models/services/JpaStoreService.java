package org.generation.italy.springdemo.models.services;

import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import org.generation.italy.springdemo.models.entities.*;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.exceptions.EntityNotFoundException;
import org.generation.italy.springdemo.models.repositories.*;
import org.generation.italy.springdemo.models.repositories.JpaOrderDetailsRepository;
import org.generation.italy.springdemo.models.repositories.specifications.ProductSpecifications;
import org.generation.italy.springdemo.models.searchCriteria.OrderFilterCriteria;
import org.generation.italy.springdemo.models.searchCriteria.ProductFilterCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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
    private JpaEmployeeRepository employeeRepo;

    @Autowired
    public JpaStoreService(JpaProductRepository productRepo, JpaCategoryRepository categoryRepo, JpaSupplierRepository supplierRepo,
                           JpaOrderRepository orderRepo, JpaOrderDetailsRepository orderDetailsRepo, JpaCustomerRepository customerRepo,
                            JpaEmployeeRepository employeeRepo){
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.supplierRepo = supplierRepo;
        this.orderRepo = orderRepo;
        this.orderDetailsRepo = orderDetailsRepo;
        this.customerRepo = customerRepo;
        this.employeeRepo = employeeRepo;
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
    public List<Product> searchProducts(ProductFilterCriteria filters) throws DataException {
//        try{
//            return productRepo.searchProductsFilters(filters);
//        } catch (PersistenceException pe){
//            throw new DataException("Errore nella ricerca die prodotti", pe);
//        }
        try {
            return productRepo.findAll(
                    Specification.where(ProductSpecifications.hasSupplierId(filters.getSupplierId()))
                            .and(ProductSpecifications.hasCategoryId(filters.getCategoryId()))
                            .and(ProductSpecifications.isMinPriceGreaterThan(filters.getMinPrice()))
                            .and(ProductSpecifications.isMaxPriceLessThan(filters.getMaxPrice()))
                            .and(ProductSpecifications.hasNameLike(filters.getNamePart()))
            );
        } catch (PersistenceException pe) {
            throw new DataException("Errore nella ricerca die prodotti", pe);
        }
    }

    @Override
    public List<Employee> searchEmployee() throws DataException {
        return employeeRepo.findAll();
    }

    @Override
    public List<Order> searchOrders(OrderFilterCriteria ofc) throws DataException {
        return orderRepo.searchOrdersFilters(ofc);
    }

    @Override
    public List<Customer> searchCustomer() throws DataException {
        return customerRepo.findAll();
    }

    @Override
    public List<Customer> findCustomerByOrderNum(Integer limite) throws DataException {
        return customerRepo.findByMaxOrders(limite);
    }

    @Override
    public List<Employee> findEmployeeByOrderNum(Integer limite) throws DataException {
        return employeeRepo.findByMaxOrders(limite);
    }

    @Override
    public Optional<Employee> findEmployeeById(int id) {
        return employeeRepo.findById(id);
    }


    @Override
    public Employee updateEmployee(Employee e, int mgrId) throws DataException, EntityNotFoundException {
        try {
            Optional<Employee> oe = employeeRepo.findById(e.getEmpId());
            if(oe.isEmpty()){
                throw new DataException("errore employee non trovato");
            }

            Optional<Employee> om = employeeRepo.findById(mgrId);
            Employee m = om.orElseThrow(()->new EntityNotFoundException(Employee.class, mgrId));

            e.setManager(m);

            return employeeRepo.save(e);
        } catch (PersistenceException pe) {
            throw new DataException("errore nella modifica di un employee", pe);
        }
    }
}

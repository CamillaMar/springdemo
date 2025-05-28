package org.generation.italy.springdemo.models.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.generation.italy.springdemo.models.entities.*;
import org.generation.italy.springdemo.models.entities.Order;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.repositories.*;
import org.generation.italy.springdemo.restdtos.ProductRestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    private EntityManager em;

    public JpaStoreService(JpaProductRepository productRepo, JpaCategoryRepository categoryRepo, JpaSupplierRepository supplierRepo, JpaCustomerRepository customerRepo, JpaOrderRepository orderRepo, EntityManager em) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.supplierRepo = supplierRepo;
        this.customerRepo = customerRepo;
        this.orderRepo = orderRepo;
        this.em = em;
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
    public List<Customer> findAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public List<Order> findByCustId(int custId) {
        return orderRepo.findByCustomerCustId(custId);
    }

    @Override
    public void deleteOrderById(Integer id) {
        orderRepo.deleteById(id);

    }

    @Override
    public Optional<Order> findOrderById(int id) {
        return orderRepo.findById(id);
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

    @Override
    public Product updateProduct(Product p,int supplierId, int categoryId) throws DataException {

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
    public List<Product> searchProducts(Integer categoryId, Integer supplierId, BigDecimal minPrice, BigDecimal maxPrice) throws DataException {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder(); //inizializza il builder della query
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class); //che tipo di entita ritornera la query, ER SELECT
        Root<Product> root = criteriaQuery.from(Product.class); // ER FROM, chi è la tabella da cui prendiamo i valori, "puntatore alla tabella con cui vogliamo lavorare"
        List<Predicate> predicates = new ArrayList<>();

        //categoryId=2 (valore in input)
        if(categoryId != null ){
            Join<Product, Category> join = root.join("category");
            Predicate predicate = criteriaBuilder.equal(join.get("categoryId"), categoryId);//il nome dell attributo della classe category
            //il valore della colonna categoryId è uguale a 2
            predicates.add(predicate);
        }

        if(supplierId != null){
            Join<Product, Supplier> join = root.join("supplierId");//non si puo fare supplier.supplierId obbligati a fare la join
            Predicate predicate = criteriaBuilder.equal(join.get("supplierId"), supplierId);
            predicates.add(predicate);
        }

        if(minPrice != null){
            Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), minPrice);
            predicates.add(predicate);
        }

        if(maxPrice != null){
            Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.get("cost"), maxPrice);
            predicates.add(predicate);
        }
        // ?categoryId=2&minPrice=10
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Product> typedQuery = em.createQuery(criteriaQuery);
        // SELECT p FROM Product p WHERE categoryId = 2 AND minPrice >= 10
        return typedQuery.getResultList();
    }

}
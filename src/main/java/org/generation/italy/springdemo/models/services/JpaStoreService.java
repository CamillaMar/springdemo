package org.generation.italy.springdemo.models.services;

import jakarta.persistence.PersistenceException;
import org.generation.italy.springdemo.models.entities.Category;
import org.generation.italy.springdemo.models.entities.Customer;
import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.entities.Supplier;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.exceptions.EntityNotFoundException;
import org.generation.italy.springdemo.models.repositories.CustomProductRepository;
import org.generation.italy.springdemo.models.repositories.JpaCategoryRepository;
import org.generation.italy.springdemo.models.repositories.JpaProductRepository;
import org.generation.italy.springdemo.models.repositories.JpaSupplierRepository;
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
    private CustomProductRepository customProductRepository;

    @Autowired
    public JpaStoreService(JpaProductRepository productRepo,
                           JpaCategoryRepository categoryRepo,
                           JpaSupplierRepository supplierRepo,
                           CustomProductRepository customProductRepository) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.supplierRepo = supplierRepo;
        this.customProductRepository=customProductRepository;
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
    public Product updateProduct(Product product, ProductRestDto dto) throws DataException, EntityNotFoundException {
        product.setProductName(dto.getProductName());
        product.setCost(dto.getUnitPrice());
        product.setDiscontinued(dto.isDiscontinued() ? 1:0);
        return saveProduct(product, dto.getSupplierId(), dto.getCategoryId());
    }

    @Override
    public List<Product> searchAllProducts(Integer supplierId, Integer categoryId, BigDecimal minPrice, BigDecimal maxPrice) throws DataException {
        return customProductRepository.searchAllProducts(supplierId, categoryId, minPrice, maxPrice);
    }

    @Override
    public Category saveCategory(Category category) throws DataException {
        return categoryRepo.save(category);
    }

    @Override
    public boolean deleteCategory(Category category) throws DataException {
        categoryRepo.delete(category);
        return true;
    }
}

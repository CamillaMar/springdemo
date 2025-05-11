package org.generation.italy.springdemo.models.services;

import jakarta.persistence.PersistenceException;
import org.generation.italy.springdemo.models.entities.*;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.repositories.*;
import org.generation.italy.springdemo.restdtos.EmployeeRestDto;
import org.generation.italy.springdemo.viewmodels.OrderViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Profile("jpa")
public class JpaStoreService implements StoreService {
    private JpaProductRepository productRepo;
    private JpaCategoryRepository categoryRepo;
    private JpaSupplierRepository supplierRepo;
    private JpaOrderRepository orderRepo;
    private JpaEmployeeRepository employeeRepo;


    @Autowired
    public JpaStoreService(JpaProductRepository productRepo, JpaCategoryRepository categoryRepo, JpaSupplierRepository supplierRepo, JpaOrderRepository orderRepo, JpaEmployeeRepository employeeRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.supplierRepo = supplierRepo;
        this.orderRepo = orderRepo;
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
    public Product saveProduct(Product p, int supplierId, int categoryId) throws DataException {
        Optional<Supplier> os = supplierRepo.findById(supplierId);
        if (os.isEmpty()) {
            throw new DataException(String.format("Il supplier con id %d non esiste", supplierId));
        }
        Supplier s = os.get();
        Optional<Category> oc = categoryRepo.findById(categoryId);
        Category c = oc.orElseThrow(() -> new DataException(String.format("la categoria con id %d non esiste", categoryId)));
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
    public List<OrderViewModel> findAllOrders() {
        List<Order> orders = orderRepo.findAll();
        return ordersToModelMapper(orders);
    }

    @Override
    public List<Employee> findAllEmployee() {
        return employeeRepo.getAllEmployee();
    }

    @Override
    public Employee findEmployeeById(int id) {
        return employeeRepo.getEmployeeById(id);
    }

    @Override
    public Employee saveEmployee(Employee e) {
        return employeeRepo.save(e);
    }

    @Override
    public Employee updateEmployee(EmployeeRestDto dto) {
        if(dto.getEmpId() == 0){
            return new Employee();
        };
        Employee e = employeeRepo.getEmployeeById(dto.getEmpId());
        if(dto.getManagerEmpId() != null && dto.getManagerEmpId() != e.getManager().getEmpId()){
            Employee m = employeeRepo.getEmployeeById(dto.getManagerEmpId());
            if(m != null){
                e.setManager(m);
            }
        }
        if(dto.getPhone() != null && !dto.getPhone().equalsIgnoreCase(e.getPhone())){
            e.setPhone(dto.getPhone());
        }
        if(dto.getCountry() != null && !dto.getCountry().equalsIgnoreCase(e.getCountry())){
            e.setCountry(dto.getCountry());
        }
        if((e.getPostalCode() == null && dto.getPostalCode() != null)|| ( dto.getPostalCode() != null && !dto.getPostalCode().equalsIgnoreCase(e.getPostalCode()))){
            e.setPostalCode(dto.getPostalCode());
        }
        if((e.getRegion() == null && dto.getRegion() != null)|| ( dto.getRegion() != null && !dto.getRegion().equalsIgnoreCase(e.getRegion()))){
            e.setRegion(dto.getRegion());
        }
        if(dto.getCity() != null && !dto.getCity().equalsIgnoreCase(e.getCity())){
            e.setCity(dto.getCity());
        }
        if(dto.getAddress() != null && !dto.getAddress().equalsIgnoreCase(e.getAddress())){
            e.setAddress(dto.getAddress());
        }
        if(dto.getHireDate() != null && !dto.getHireDate().isEqual(e.getHireDate())){
            e.setHireDate(dto.getHireDate());
        }
        if(dto.getBirthDate() != null && !dto.getBirthDate().isEqual(e.getBirthDate())){
            e.setBirthDate(dto.getBirthDate());
        }
        if(dto.getTitleOfCourtesy() != null && !dto.getTitleOfCourtesy().equalsIgnoreCase(e.getTitleOfCourtesy())){
            e.setTitleOfCourtesy(dto.getTitleOfCourtesy());
        }
        if(dto.getTitle() != null && !dto.getTitle().equalsIgnoreCase(e.getTitle())){
            e.setTitle(dto.getTitle());
        }
        if(dto.getFirstName() != null && !dto.getFirstName().equalsIgnoreCase(e.getFirstName())){
            e.setFirstName(dto.getFirstName());
        }
        if(dto.getLastName() != null && !dto.getLastName().equalsIgnoreCase(e.getLastName())){
            e.setLastName(dto.getLastName());
        }
        employeeRepo.saveAndFlush(e);
        return e;
    }

    @Override
    public void deleteEmployee(int id) throws DataException {
       Integer count = employeeRepo.deleteEmployee(id);
       if(count == 0){
           throw new DataException("Id non presente nel database");
       }
    }

    private List<OrderViewModel> ordersToModelMapper(List<Order> orders){
        List<OrderViewModel> listToReturn = new ArrayList<>();
        for(Order o:orders){
            OrderViewModel ovm = new OrderViewModel(o.getOrderId(), o.getShipCountry(), o.getShipPostalCode(), o.getShipRegion(), o.getShipCity(),o.getShipAddress(), o.getShipName(), o.getFreight(), o.getShipper().getShipperId(), o.getShippedDate(), o.getRequiredDate(), o.getEmployee().getEmpId(), o.getCustomer().getCustId() );
            listToReturn.add(ovm);
        }
        return listToReturn;
    }


}

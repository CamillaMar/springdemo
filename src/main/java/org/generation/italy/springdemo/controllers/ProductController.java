package org.generation.italy.springdemo.controllers;

import org.generation.italy.springdemo.models.entities.Category;
import org.generation.italy.springdemo.models.entities.Customer;
import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.entities.Supplier;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.services.StoreService;
import org.generation.italy.springdemo.viewmodels.ProductViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    private StoreService storeService;
    public ProductController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/show-add-product-form")
    public String showAddProductForm( Model model){
        ProductViewModel pvm = new ProductViewModel();
        List<Category> categories = storeService.findAllCategories();
        List<Supplier> suppliers = storeService.findAllSuppliers();
        pvm.setCategories(categories);
        pvm.setSuppliers(suppliers);
        model.addAttribute("product", pvm);
        return "product/forms/add-product-form";
    }
    @PostMapping("/add-product")
    public String addProduct(ProductViewModel pvm){
        try {
            Product p = pvm.toProduct();
            storeService.saveProduct(p, pvm.getSupplierId(), pvm.getCategoryId());
            return "redirect:/product";
        } catch (DataException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/show-search-form")
    public String showSearchForm(){
        return "product/forms/show-search-form";
    }
    @GetMapping("/product")
    public String searchProducts(@RequestParam(required = false) String name,@RequestParam(required = false) Integer discontinued, Model model){
        try{
            List<Product> result = null;
            if(name != null && !name.equals("") ) {
                result = storeService.findByProductNameContains(name);
            }else if(discontinued != null && discontinued != -1){
                result = storeService.findProductsByDiscontinued(discontinued);
            }else {
                result = storeService.findAllProducts();
            }
            model.addAttribute("products",result);
            System.out.println(result + "-------------------");
            return "product/show-products";
        }catch(DataException e){
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/show-byId-form")
    public String showByIdForm(){
        return "product/forms/select-product-form";
    }
    @GetMapping("/product/byId")
    public String showProduct(@RequestParam Integer idInput, Model model){
        System.out.println("show product ---------------------------");
        try {
            Optional<Product> op = storeService.findProductById(idInput);
            if(op.isPresent()) {
                model.addAttribute("product", op.get());
                return "product/show-product";
            }else{
                return "product/missing-product";
            }
        } catch (DataException e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }
}

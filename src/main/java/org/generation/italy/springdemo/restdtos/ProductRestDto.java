package org.generation.italy.springdemo.restdtos;

import org.generation.italy.springdemo.models.entities.Category;
import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.entities.Supplier;

import java.math.BigDecimal;
import java.util.List;

public class ProductRestDto {
    private int productId;
    private String productName;
    private int supplierId;
    private int categoryId;
    private BigDecimal unitPrice;
    private boolean discontinued;

    private List<Category> categories;
    private List<Supplier> suppliers;

    public ProductRestDto() {
    }

    public ProductRestDto(int productId, String productName, int supplierId, int categoryId, BigDecimal unitPrice, boolean discontinued) {
        this.productId = productId;
        this.productName = productName;
        this.supplierId = supplierId;
        this.categoryId = categoryId;
        this.unitPrice = unitPrice;
        this.discontinued = discontinued;
    }

    @Override
    public String toString() {
        return "ProductViewModel{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", supplierId=" + supplierId +
                ", categoryId=" + categoryId +
                ", unitPrice=" + unitPrice +
                ", discontinued=" + discontinued +
                '}';
    }

    public Product toProduct(){
        Product p = new Product(productId, productName, null, null, unitPrice, discontinued ? 1 : 0);
        return p;
    }

    public static ProductRestDto toDto(Product p){
        return new ProductRestDto(p.getProductId(), p.getProductName(), p.getSupplier().getSupplierId(), p.getCategory().getCategoryId(), p.getUnitPrice(), p.getDiscontinued() == 1);
    }

    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getSupplierId() {
        return supplierId;
    }
    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public boolean getDiscontinued() {
        return discontinued;
    }
    public void setDiscontinued(boolean discontinued) {
        this.discontinued = discontinued;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }
}

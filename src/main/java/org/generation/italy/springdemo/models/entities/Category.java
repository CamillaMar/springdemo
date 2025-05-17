package org.generation.italy.springdemo.models.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryid")
    private int categoryId;

    @Column (name = "categoryname")
    private String categoryName;

    private String description;

    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();

    public Category() {
    }
    public Category(String categoryName, String description) {
        this.categoryName = categoryName;
        this.description = description;
    }
    public Category(int id, String categoryName, String description, List<Product> products) {
        this.categoryId = id;
        this.categoryName = categoryName;
        this.description = description;
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}

package org.generation.italy.springdemo.restdtos;

import org.generation.italy.springdemo.models.entities.Category;
import org.generation.italy.springdemo.models.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class CategoryRestDto {
    private int categoryId;
    private String categoryName;
    private String description;
    //private List<Product> products = new ArrayList<>();

    public CategoryRestDto() {
    }

    public CategoryRestDto(int categoryId, String categoryName, String description) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
    }

    public Category toCategory(){
        return new Category(categoryId, categoryName, description, null);
    }

    public static CategoryRestDto toDto(Category c) {
        return new CategoryRestDto(c.getCategoryId(), c.getCategoryName(), c.getDescription());
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

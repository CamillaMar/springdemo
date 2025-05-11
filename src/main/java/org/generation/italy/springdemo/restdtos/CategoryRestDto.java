package org.generation.italy.springdemo.restdtos;

import jakarta.persistence.Column;
import org.generation.italy.springdemo.models.entities.Category;

public class CategoryRestDto {
    private Integer categoryId;
    private String categoryName;
    private String description;

    public CategoryRestDto(){}

    public CategoryRestDto(int categoryId, String categoryName, String description) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
    }

    public static CategoryRestDto toDto(Category category){
        return new CategoryRestDto(category.getCategoryId(), category.getCategoryName(), category.getDescription());
    }

    public Category toCategory(){
        return new Category(categoryName, description);
    }

    public Integer getCategoryId() {
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

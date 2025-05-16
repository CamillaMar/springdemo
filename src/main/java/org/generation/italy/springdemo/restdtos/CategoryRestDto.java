package org.generation.italy.springdemo.restdtos;

import org.generation.italy.springdemo.models.entities.Category;

public class CategoryRestDto {
    private int categoryId;
    private String categoryName;
    private String description;

    public CategoryRestDto() {
    }

    public CategoryRestDto(int categoryId, String categoryName, String description) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
    }

    public Category toCategory(){
        return new Category(categoryId, categoryName, description);
    }

    public static CategoryRestDto toDto(Category c) {
        return new CategoryRestDto(c.getCategoryId(), c.getCategoryName(), c.getDescription());
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getDescription() {
        return description;
    }
}

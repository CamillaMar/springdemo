package org.generation.italy.springdemo.restdtos;

import org.generation.italy.springdemo.models.entities.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryRestDto {
    private int categoryId;
    private String categoryName;
    private String description;

    public CategoryRestDto(int categoryId, String cateogryName, String description) {
        this.categoryId = categoryId;
        this.categoryName = cateogryName;
        this.description = description;
    }

    public CategoryRestDto() {
    }

    static public CategoryRestDto toDto(Category category){
        return new CategoryRestDto(category.getCategoryId(),category.getCategoryName(),category.getDescription());
    }

    static public List<CategoryRestDto> listToDtoList (List<Category> catList){
        List<CategoryRestDto> ret= new ArrayList();
        for (Category cat : catList){
            ret.add(toDto(cat));
        }
        return ret;
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

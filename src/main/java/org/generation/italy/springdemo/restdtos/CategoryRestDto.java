
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

    @Override
    public String toString() {
        return "CategoryRestDto{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categoryDescription='" + description + '\'' +
                '}';
    }

    public Category toCategory(){
        Category c = new Category(categoryId, categoryName, description);
        return c;
    }

    public static CategoryRestDto toDto(Category c){
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


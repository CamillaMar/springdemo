package org.generation.italy.springdemo.models.entities;

import jakarta.persistence.*;
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

    //quando carichiamo una lista di categorie vogliamo sempre vedere tutti i prodotti per tutte le categorie
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Product> products;

    public Category() {
    }

    public Category(String categoryName, String description) {
        this.categoryName = categoryName;
        this.description = description;
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

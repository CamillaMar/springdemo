package org.generation.italy.springdemo.models.dtos;

public class SelectListElement {
    private int id;
    private String description;

    public SelectListElement(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }
}

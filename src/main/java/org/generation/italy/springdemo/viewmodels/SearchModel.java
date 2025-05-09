package org.generation.italy.springdemo.viewmodels;

import org.generation.italy.springdemo.models.entities.Customer;

public class SearchModel {
    private int id;
    private String name;

    public SearchModel(){

    }
    public SearchModel(int id, String name){
        this.id = id;
        this.name = name;
    }

    public static SearchModel fromCustomer(Customer customer){
        return new SearchModel(customer.getCustId(), customer.getCompanyName());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

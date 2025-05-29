package org.generation.italy.springdemo.models.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String lastname;
    private String gender;
    private LocalDate birthdate;

    @Column(name = "favorite_language")
    private String favoriteLanguage;

    public Student() {
    }

    public Student(int id, String name, String lastname,
                   String gender, LocalDate birthdate, String favoriteLanguage) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.gender = gender;
        this.birthdate = birthdate;
        this.favoriteLanguage = favoriteLanguage;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getGender() {
        return gender;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getFavoriteLanguage() {
        return favoriteLanguage;
    }
}

package org.generation.italy.springdemo.models.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String lastname;
    private String gender;
    @Column(name = "birthdate")
    private LocalDate birthDate;
    @Column(name = "favorite_language")
    private String favoriteLanguage;

    public Student() {
    }

    public Student(Integer id, String name, String lastname, String gender, LocalDate birthDate, String favoriteLanguage) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.gender = gender;
        this.birthDate = birthDate;
        this.favoriteLanguage = favoriteLanguage;
    }

    public Integer getId() {
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getFavoriteLanguage() {
        return favoriteLanguage;
    }
}

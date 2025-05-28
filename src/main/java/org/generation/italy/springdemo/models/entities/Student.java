package org.generation.italy.springdemo.models.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name= "students")
public class Student {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
  private int id;
  private String name;
  @Column(name = "last_name")
  private String lastName;
  private String gender;
  @Column(name = "birth_date")
  private LocalDate birthDate;
  @Column(name = "favourite_language")
  private String favouriteLanguage;


    public Student() {
    }

    public Student(int id, String name, String lastName, String gender, LocalDate birthDate, String favouriteLanguage) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.favouriteLanguage = favouriteLanguage;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getFavouriteLanguage() {
        return favouriteLanguage;
    }
}

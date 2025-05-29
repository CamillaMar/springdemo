package org.generation.italy.springdemo.restdtos;

import org.generation.italy.springdemo.models.entities.Student;

import java.time.LocalDate;

public class StudentRestDto {
    private int id;
    private String name;
    private String lastname;
    private String gender;
    private LocalDate birthDate;
    private String favoriteLanguage;

    public StudentRestDto() {
    }

    public StudentRestDto(int id, String name, String lastname,
                          String gender, LocalDate birthDate, String favoriteLanguage) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.gender = gender;
        this.birthDate = birthDate;
        this.favoriteLanguage = favoriteLanguage;
    }

    public static StudentRestDto toDto(Student s) {
        return new StudentRestDto(s.getId(), s.getName(), s.getLastname(), s.getGender(), s.getBirthdate(), s.getFavoriteLanguage());
    }

    public Student toStudent() {
        return new Student(id, name, lastname, gender, birthDate, favoriteLanguage);
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getFavoriteLanguage() {
        return favoriteLanguage;
    }
}
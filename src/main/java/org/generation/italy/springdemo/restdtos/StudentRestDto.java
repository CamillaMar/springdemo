package org.generation.italy.springdemo.restdtos;

import org.generation.italy.springdemo.models.entities.Student;

import java.time.LocalDate;

public class StudentRestDto {
    private int studentId;
    private String firstname;
    private String lastname;
    private String gender;
    private LocalDate birthdate;
    private String favoriteLanguage;

    public StudentRestDto() {
    }

    public StudentRestDto(int studentId, String firstname, String lastname,
                          String gender, LocalDate birthdate, String favoriteLanguage) {
        this.studentId = studentId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.birthdate = birthdate;
        this.favoriteLanguage = favoriteLanguage;
    }

    public static StudentRestDto toDto(Student s) {
        return new StudentRestDto(s.getId(), s.getName(), s.getLastname(), s.getGender(), s.getBirthDate(), s.getFavoriteLanguage());
    }

    public Student toStudent() {
        return new Student(studentId, firstname, lastname, gender, birthdate, favoriteLanguage);
    }

    public int getStudentId() {
        return studentId;
    }

    public String getFirstname() {
        return firstname;
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

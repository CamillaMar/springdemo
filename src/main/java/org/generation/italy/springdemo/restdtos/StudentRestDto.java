package org.generation.italy.springdemo.restdtos;

import org.generation.italy.springdemo.models.entities.Student;

import java.sql.Date;

public class StudentRestDto {
    private int studentId;
    private String firstname;
    private String lastname;
    private String gender;
    private Date birthdate;
    private String favoriteLanguage;

    public StudentRestDto() {
    }

    public StudentRestDto(int studentId, String firstname, String lastname,
                   String gender, Date birthdate, String favoriteLanguage) {
        this.studentId = studentId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.birthdate = birthdate;
        this.favoriteLanguage = favoriteLanguage;
    }

    public static StudentRestDto toDto(Student s) {
        return new StudentRestDto(s.getStudentId(), s.getFirstname(), s.getLastname(), s.getGender(), s.getBirthdate(), s.getFavoriteLanguage());
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

    public Date getBirthdate() {
        return birthdate;
    }

    public String getFavoriteLanguage() {
        return favoriteLanguage;
    }
}
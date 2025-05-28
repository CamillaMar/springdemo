package org.generation.italy.springdemo.models.entities;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studentid")
    private int studentId;

    private String firstname;
    private String lastname;
    private String gender;
    private Date birthdate;

    @Column(name = "favoritelanguage")
    private String favoriteLanguage;

    public Student() {
    }

    public Student(int studentId, String firstname, String lastname,
                   String gender, Date birthdate, String favoriteLanguage) {
        this.studentId = studentId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.birthdate = birthdate;
        this.favoriteLanguage = favoriteLanguage;
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

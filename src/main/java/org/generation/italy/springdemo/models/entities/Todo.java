package org.generation.italy.springdemo.models.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todoid")
    private int todoId;

    private String title;

    private String description;

    @Column(name = "creationdate")
    private LocalDate creationDate;

    @Column(name = "iscomplete")
    private boolean isComplete;

    @Column(name = "completiondate")
    private LocalDate completionDate;

    public Todo(){}

    public Todo(int todoId, String title, String description, LocalDate creationDate, boolean isComplete, LocalDate completionDate) {
        this.todoId = todoId;
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.isComplete = isComplete;
        this.completionDate = completionDate;
    }

    public int getTodoId() {
        return todoId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }
}

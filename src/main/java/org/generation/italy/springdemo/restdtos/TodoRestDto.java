package org.generation.italy.springdemo.restdtos;
import org.generation.italy.springdemo.models.entities.Todo;

import java.time.LocalDate;
import java.util.Date;

public class TodoRestDto {

    private int todoId;
    private String title;
    private String description;
    private LocalDate creationDate;
    private boolean isComplete;
    private LocalDate completionDate;

    public TodoRestDto(){}

    public TodoRestDto(int todoId, String title, String description, LocalDate creationDate, boolean isComplete, LocalDate completionDate) {
        this.todoId = todoId;
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.isComplete = isComplete;
        this.completionDate = completionDate;
    }

    public Todo toTodo(){
        return new Todo(todoId, title, description, creationDate, isComplete, completionDate);
    }

    public static TodoRestDto toDto(Todo todo){
        return new TodoRestDto(todo.getTodoId(), todo.getTitle(), todo.getDescription(), todo.getCreationDate(), todo.isComplete(), todo.getCompletionDate());
    }

    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }
}

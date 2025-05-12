package org.generation.italy.springdemo.models.exceptions;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(Class<?> entityClass, int id) {
        super(String.format("L'entità %s con id %d non esiste", entityClass.getSimpleName(), id));
    }
}

package org.generation.italy.springdemo.models.exceptions;

import org.generation.italy.springdemo.models.entities.Supplier;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(Class<?> entityClass, int id) {
        super(String.format("La Entity %s con Id %d non esiste", entityClass.getSimpleName(), id));
    }
}
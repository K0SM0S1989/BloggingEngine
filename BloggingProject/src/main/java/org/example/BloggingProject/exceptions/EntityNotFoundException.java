package org.example.BloggingProject.exceptions;

public class EntityNotFoundException extends Exception{
    private String message;
    private int entityId;


    public static EntityNotFoundException createWith(int entityId, String message) {
        return new EntityNotFoundException(entityId, message);
    }

    public EntityNotFoundException(int entityId, String message) {
        this.entityId = entityId;
        this.message = message;
    }


    @Override
    public String getMessage() {
        return message;
    }
}

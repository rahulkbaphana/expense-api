package org.boot.services.expense.exception;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String entity, String value) {
        super(String.format("Requested %s entity having id %s does not exists in the system.",entity,value));
    }
}

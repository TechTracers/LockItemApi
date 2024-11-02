package com.techtracers.lockitemapi.shared.exception;

public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException(String message) {
        super(message);
    }

    ResourceAlreadyExistsException() {
        super();
    }
}

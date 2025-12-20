package com.ceniuch.bluediamondbackend.subjects.exceptions;

public class SubjectExistsException extends RuntimeException {
    public SubjectExistsException(String message) {
        super(message);
    }
}

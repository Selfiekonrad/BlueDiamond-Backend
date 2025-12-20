package com.ceniuch.bluediamondbackend.subjects.exceptions;

import com.ceniuch.bluediamondbackend.errors.ErrorMessageModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SubjectExceptionControllerAdvice {

    @ExceptionHandler
    ResponseEntity<ErrorMessageModel> handleSubjectNotFoundException(SubjectNotFoundException exception) {
        ErrorMessageModel errorMessageModel = new ErrorMessageModel(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage()
        );
        return new ResponseEntity<>(errorMessageModel, HttpStatus.NOT_FOUND);
    }
}

package com.study.exceptionpractice.web.exception.advice;

import com.study.exceptionpractice.web.exception.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

//@RestControllerAdvice
public class MemberExAdvice {

    @ExceptionHandler
    public ResponseEntity<ErrorResult> bindExceptionExHandler(BindException e) {
        ErrorResult errorResult = new ErrorResult(String.valueOf(HttpStatus.BAD_REQUEST.value()), "잘못된 요청입니다.");

        List<FieldError> fieldErrors = e.getFieldErrors();

        fieldErrors.stream().forEach(field -> errorResult.addFieldError(field.getField(), field.getDefaultMessage()));

        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }
}

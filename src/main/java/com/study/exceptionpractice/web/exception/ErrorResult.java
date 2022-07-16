package com.study.exceptionpractice.web.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class ErrorResult {

    private final String code;
    private final String message;
    private final List<FieldError> fieldErrors = new ArrayList<>();

    public void addFieldError(String field, String message){
        fieldErrors.add(new FieldError(field, message));
    }

}

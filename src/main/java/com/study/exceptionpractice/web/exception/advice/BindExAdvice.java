package com.study.exceptionpractice.web.exception.advice;

import com.study.exceptionpractice.web.controller.Item.ItemApiController;
import com.study.exceptionpractice.web.controller.member.MemberApiController;
import com.study.exceptionpractice.web.exception.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import java.util.List;

@RestControllerAdvice
public class BindExAdvice {

    @ExceptionHandler
    public ResponseEntity<ErrorResult> bindExceptionExHandler(BindException e, HandlerMethod handlerMethod) {

        String errorMessage = null;

        if(handlerMethod.getBean() instanceof ItemApiController){
            errorMessage = "상품 등록 시 잘못된 요청입니다.";
        }
        if(handlerMethod.getBean() instanceof MemberApiController){
            errorMessage = "회원 등록 시 잘못된 요청입니다.";
        }

        ErrorResult errorResult = new ErrorResult(String.valueOf(HttpStatus.BAD_REQUEST.value()), errorMessage);

        List<FieldError> fieldErrors = e.getFieldErrors();

        fieldErrors.stream().forEach(field -> errorResult.addFieldError(field.getField(), field.getDefaultMessage()));

        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

}

package com.project.legendsofleague.common.exception;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {CustomException.class})
    protected ResponseEntity<List<String>> handleCustomException(CustomException ex) {
        List<String> messageList = List.of(ex.getMessage());
        return new ResponseEntity<List<String>>(messageList, ex.getHttpStatus());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    protected ResponseEntity<List<String>> handleBindingException(
        MethodArgumentNotValidException e) {
        List<String> messageList = e.getBindingResult().getFieldErrors().stream()
            .map(FieldError::getDefaultMessage)
            .collect(Collectors.toList());
        return new ResponseEntity<List<String>>(messageList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<List<String>> handleException(Exception ex) {
        String message = "오류 발생";
        return new ResponseEntity<List<String>>(List.of(message), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

package com.teste.sas.testeSas.handler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.teste.sas.testeSas.dto.ErrorResponse;
import com.teste.sas.testeSas.dto.ErrorResponse.FieldValidationMessageResponse;
import com.teste.sas.testeSas.utils.UtilsTesteSas;

import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class ErrorHandlers {

    @ExceptionHandler({BusinessExpection.class })
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handle(BusinessExpection exception) {
        
        return ErrorResponse.builder()
        		.status(HttpStatus.UNPROCESSABLE_ENTITY.value())
        		.message(exception.getMessage())
        		.timestamp(LocalDateTime.now())
        		.build();
        		
    }  
    
    @ExceptionHandler({MethodArgumentNotValidException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handle(MethodArgumentNotValidException exception) {
        
    	List<FieldValidationMessageResponse> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> FieldValidationMessageResponse.builder()
                    .field(error.getField())
                    .message(error.getDefaultMessage())
                    .build())
                .collect(Collectors.toList());
    	
        return ErrorResponse.builder()
        		.status(HttpStatus.BAD_REQUEST.value())
        		.message(UtilsTesteSas.METHOD_ARGUMENT_NOT_VALID_MESSAGE)
        		.timestamp(LocalDateTime.now())
        		.errors(errors)
        		.build();
        		
    }  
    
    @ExceptionHandler({InternalServerError.class, Exception.class, IllegalStateException.class, IllegalArgumentException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handle(Exception exception) {
        
        return ErrorResponse.builder()
        		.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        		.message(UtilsTesteSas.INTERNAL_SERVER_ERROR_MESSAGE)
        		.timestamp(LocalDateTime.now())
        		.build();
        		
    }   
	
}

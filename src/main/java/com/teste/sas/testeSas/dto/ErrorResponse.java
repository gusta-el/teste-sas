package com.teste.sas.testeSas.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@Value
@Builder
@RequiredArgsConstructor
@JsonInclude(Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ErrorResponse {
	
		Integer status;
	    String message;
	    LocalDateTime timestamp;
	    List<FieldValidationMessageResponse> errors;
	    
	    
	    @Value
	    @Builder
	    @RequiredArgsConstructor
	    public static class FieldValidationMessageResponse {
	    	
	        String field;
	        String message;
	    }
	
}

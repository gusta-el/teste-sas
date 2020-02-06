package com.teste.sas.testeSas.handler;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.teste.sas.testeSas.dto.ErrorResponse;
import com.teste.sas.testeSas.utils.UtilsTesteSas;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class ErrorHandlersTest {

    @InjectMocks
    private ErrorHandlers errorHandler;
    
    @Test
    public void businessExpectionHandlerTest(){
    	    	
    	ErrorResponse errorResponse = errorHandler.handle(new BusinessExpection(UtilsTesteSas.PROVA_NAO_ENCONTRADA_MESSAGE));
    	assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), errorResponse.getStatus().intValue());
    	assertEquals(UtilsTesteSas.PROVA_NAO_ENCONTRADA_MESSAGE, errorResponse.getMessage());
    	
    }

    @Test
    public void expectionHandlerTest(){
    	    	
    	ErrorResponse errorResponse = errorHandler.handle(new Exception(UtilsTesteSas.INTERNAL_SERVER_ERROR_MESSAGE));
    	assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorResponse.getStatus().intValue());
    	assertEquals(UtilsTesteSas.INTERNAL_SERVER_ERROR_MESSAGE, errorResponse.getMessage());
    	
    }
    
}

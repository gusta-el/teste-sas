package com.teste.sas.testeSas.service;


import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.teste.sas.testeSas.handler.BusinessExpection;
import com.teste.sas.testeSas.repository.ProvaRepository;
import com.teste.sas.testeSas.service.impl.ProvaServiceImpl;
import com.teste.sas.testeSas.utils.UtilsTesteSas;


@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class ProvaServiceImplTest {

	@Mock
	ProvaRepository provaRepository;
	
    @InjectMocks
    private ProvaServiceImpl provaService;
      
    @Test
    public void provaNaoEncontradaProvaServiceTest(){
    	
    	when(provaRepository.findById(any())).thenReturn(Optional.empty());
    
		try {
			provaService.execute(1L);
		} catch (BusinessExpection e) {
			assertEquals(UtilsTesteSas.PROVA_NAO_ENCONTRADA_MESSAGE, e.getMessage());
			verify(provaRepository, times(1)).findById(any());
		}
    	 
    }
    
}

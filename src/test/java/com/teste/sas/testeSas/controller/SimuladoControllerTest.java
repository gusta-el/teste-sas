package com.teste.sas.testeSas.controller;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;

import com.teste.sas.testeSas.dto.SimuladoDTO;
import com.teste.sas.testeSas.dto.SimuladoDTO.ProvaDTO;
import com.teste.sas.testeSas.handler.ErrorHandlers;
import com.teste.sas.testeSas.service.SimuladoService;

@AutoConfigureRestDocs
@WebMvcTest({ SimuladoController.class, ErrorHandlers.class })
public class SimuladoControllerTest {

	@Autowired
	private MockMvc client;

	@MockBean
	private SimuladoService simuladoService;	
		
	@Test
	public void consultarSimuladosTest() throws Exception {

		when(simuladoService.execute()).thenReturn(this.mockSimuladosDto());
		
		this.client
        .perform(get("/teste-sas/simulados"))
        .andDo(print())
        .andDo(document("simulado-controller/query",
            responseFields(
            		subsectionWithPath("[].nomeSimulado").description("Nome do simulado aplicado.").type(JsonFieldType.STRING),
            		subsectionWithPath("[].dataDeAplicacao").description("Data de aplicação do simulado.").type(JsonFieldType.STRING),
            		subsectionWithPath("[].quantidadeDeProvas").description("Quantidade de Provas.").type(JsonFieldType.NUMBER),
            		subsectionWithPath("[].provas").description("Provas aplicados do simulado.").type(JsonFieldType.ARRAY),
						subsectionWithPath("[].provas[].idProva").description("Número identificador da prova.").type(JsonFieldType.NUMBER),
						subsectionWithPath("[].provas[].nomeProva").description("Nome da prova aplicada.").type(JsonFieldType.STRING)
            		)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].nomeSimulado").value("Ciências da Natureza"))
        .andExpect(jsonPath("$.[0].dataDeAplicacao").value(LocalDate.now().toString()))
        .andExpect(jsonPath("$.[0].quantidadeDeProvas").value(3))
	        .andExpect(jsonPath("$.[0].provas[1].idProva").value(2))
	        .andExpect(jsonPath("$.[0].provas[1].nomeProva").value("Português"));
		
		verify(simuladoService, times(1)).execute();
		verify(simuladoService, times(0)).execute(any());
		
	}
	
	@Test
	public void consultarSimuladoTest() throws Exception {

		when(simuladoService.execute(any())).thenReturn(new SimuladoDTO("Ciências da Natureza", LocalDate.now(), 3, this.mockProvasDto()));
		
		this.client
        .perform(get("/teste-sas/simulados/1"))
        .andDo(print())
        .andDo(document("simulado-controller/query",
            responseFields(
            		fieldWithPath("nomeSimulado").description("Nome do simulado aplicado.").type(JsonFieldType.STRING),
            		fieldWithPath("dataDeAplicacao").description("Data de aplicação do simulado.").type(JsonFieldType.STRING),
            		fieldWithPath("quantidadeDeProvas").description("Quantidade de Provas.").type(JsonFieldType.NUMBER),
            		fieldWithPath("provas").description("Provas aplicados do simulado.").type(JsonFieldType.ARRAY),
						subsectionWithPath("provas[].idProva").description("Número identificador da prova.").type(JsonFieldType.NUMBER),
						subsectionWithPath("provas[].nomeProva").description("Nome da prova aplicada.").type(JsonFieldType.STRING)
            		)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nomeSimulado").value("Ciências da Natureza"))
        .andExpect(jsonPath("$.dataDeAplicacao").value(LocalDate.now().toString()))
        .andExpect(jsonPath("$.quantidadeDeProvas").value(3))
	        .andExpect(jsonPath("$.provas[1].idProva").value(2))
	        .andExpect(jsonPath("$.provas[1].nomeProva").value("Português"));
		
		verify(simuladoService, times(0)).execute();
		verify(simuladoService, times(1)).execute(any());
		
	}
	
	private List<ProvaDTO> mockProvasDto(){
		return Arrays.asList(ProvaDTO.builder().idProva(1L)
				.nomeProva("Matemática").build(),
				ProvaDTO.builder().idProva(2L)
				.nomeProva("Português").build());
	}
	
	private List<SimuladoDTO> mockSimuladosDto(){
		return Arrays.asList(new SimuladoDTO("Ciências da Natureza", LocalDate.now(), 3, this.mockProvasDto()),
							new SimuladoDTO("Matemática e Suas Tecnologias", LocalDate.now(), 2, this.mockProvasDto()),
							new SimuladoDTO("Linguagens Códigos e Suas Tecnologias", LocalDate.now(), 7, this.mockProvasDto()));
	
		
	}
	
	
}

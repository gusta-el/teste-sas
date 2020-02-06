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

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;

import com.teste.sas.testeSas.dto.RankingDTO;
import com.teste.sas.testeSas.dto.RankingDTO.AlunoDTO;
import com.teste.sas.testeSas.handler.ErrorHandlers;
import com.teste.sas.testeSas.service.RankingService;

@AutoConfigureRestDocs
@WebMvcTest({ RankingController.class, ErrorHandlers.class })
public class RankingControllerTest {

	@Autowired
	private MockMvc client;

	@MockBean
	private RankingService rankingService;
	
	@Test
	public void consultarClassificaoGeralTest() throws Exception {

		when(rankingService.execute(any())).thenReturn(RankingDTO.builder()
															.nomeSimulado("Ciências da Natureza")
															.ranking(this.mockAlunosDto()).build());
		this.client
        .perform(get("/teste-sas/ranking/1"))
        .andDo(print())
        .andDo(document("ranking-controller/query",
            responseFields(
            		fieldWithPath("nomeSimulado").description("Nome do simulado aplicado.").type(JsonFieldType.STRING),
            		fieldWithPath("ranking").description("Ranking com os 5 melhores colocados.").type(JsonFieldType.ARRAY),
						subsectionWithPath("ranking[].aluno").description("Nome do aluno").type(JsonFieldType.STRING),
						subsectionWithPath("ranking[].notaFinal").description("Média final").type(JsonFieldType.NUMBER),
						subsectionWithPath("ranking[].classificacaoGeral").description("Classificação Geral").type(JsonFieldType.NUMBER)
            		)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nomeSimulado").value("Ciências da Natureza"));
		
		verify(rankingService, times(1)).execute(any());
		
	}
	
	private List<AlunoDTO> mockAlunosDto(){
		return Arrays.asList(AlunoDTO.builder().aluno("Gustavo")
				.classificacaoGeral(1)
				.notaFinal(123).build(), AlunoDTO.builder().aluno("Carmen")
				.classificacaoGeral(2)
				.notaFinal(120).build());
	}
	
}
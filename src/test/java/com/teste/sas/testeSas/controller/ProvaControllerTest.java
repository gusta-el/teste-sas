package com.teste.sas.testeSas.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;

import com.teste.sas.testeSas.dto.Complexidade;
import com.teste.sas.testeSas.dto.RespostaOutDTO;
import com.teste.sas.testeSas.handler.ErrorHandlers;
import com.teste.sas.testeSas.service.ProvaService;
import com.teste.sas.testeSas.service.QuestaoService;

@AutoConfigureRestDocs
@WebMvcTest({ ProvaController.class, ErrorHandlers.class })
public class ProvaControllerTest {
	
	@Autowired
	private MockMvc client;

	@MockBean
	ProvaService provaService;

	@MockBean
	QuestaoService questaoService;
	
	@Test
	public void responderQuestaoTest() throws Exception {

		when(questaoService.execute(any())).thenReturn(RespostaOutDTO.builder()
				.alternativaCorreta('C')
				.alternativaEscolhida('C')
				.complexidade(Complexidade.DIFÍCIL)
				.numeroQuestao(1)
				.build());

		this.client
        .perform(get("/teste-sas/provas/1"))
        .andDo(print())
        .andDo(document("prova-controller/query",
            responseFields(
            		fieldWithPath("nomeProva").description("Nome do simulado aplicado.").type(JsonFieldType.STRING),
            		fieldWithPath("gabarito").description("Gabarito com todas as respostas corretas do simulado").type(JsonFieldType.ARRAY),
						subsectionWithPath("gabarito[].nivel").description("Nome do aluno").type(JsonFieldType.NUMBER),
						subsectionWithPath("gabarito[].numeroQuestao").description("Média final").type(JsonFieldType.NUMBER),
						subsectionWithPath("gabarito[].enunciado").description("Classificação Geral").type(JsonFieldType.STRING),
						subsectionWithPath("gabarito[].alternativaCorreta").description("Alternativa correta da questão").type(JsonFieldType.STRING)
            		)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nivel").value("Ciências da Natureza"))
		.andExpect(jsonPath("$.numeroQuestao").value(1))
		.andExpect(jsonPath("$.enunciado").value(Complexidade.DIFÍCIL))
		.andExpect(jsonPath("$.alternativaCorreta").value('C'));
		
		verify(provaService, times(1)).execute(any());
		
	}
	
	
}

package com.teste.sas.testeSas.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;

import com.teste.sas.testeSas.dto.Complexidade;
import com.teste.sas.testeSas.dto.GabaritoDTO;
import com.teste.sas.testeSas.dto.GabaritoDTO.QuestaoDTO;
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
	public void verificarGabarito() throws Exception {

		when(provaService.execute(any())).thenReturn(new GabaritoDTO("Matemática", this.mockQuestaoDto()));
								
		this.client
        .perform(get("/teste-sas/provas/1"))
        .andDo(print())
        .andDo(document("prova-controller/query",
            responseFields(
            		fieldWithPath("nomeProva").description("Nome do simulado aplicado.").type(JsonFieldType.STRING),
            		fieldWithPath("gabarito").description("Gabarito com todas as respostas corretas do simulado").type(JsonFieldType.ARRAY),
						subsectionWithPath("gabarito[].nivel").description("Nome do aluno").type(JsonFieldType.STRING),
						subsectionWithPath("gabarito[].numeroQuestao").description("Média final").type(JsonFieldType.NUMBER),
						subsectionWithPath("gabarito[].enunciado").description("Classificação Geral").type(JsonFieldType.STRING),
						subsectionWithPath("gabarito[].alternativaCorreta").description("Alternativa correta da questão").type(JsonFieldType.STRING)
            		)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nomeProva").value("Matemática"))
        .andExpect(jsonPath("$.gabarito[0].nivel").value("DIFÍCIL"))
        .andExpect(jsonPath("$.gabarito[0].numeroQuestao").value(1))
        .andExpect(jsonPath("$.gabarito[0].enunciado").value("Lorem ipsulm"))
        .andExpect(jsonPath("$.gabarito[0].alternativaCorreta").value("C"));
		
        verify(provaService, times(1)).execute(any());
		
	}
	
	@Test
	public void responderQuestao() throws Exception {

		when(questaoService.execute(any())).thenReturn(RespostaOutDTO.builder()
				.alternativaCorreta('C')
				.alternativaEscolhida('C')
				.complexidade(Complexidade.DIFÍCIL)
				.numeroQuestao(1)
				.build());

		this.client
        .perform(post("/teste-sas/provas/1/responder-questao").header("Content-Type", MediaType.APPLICATION_JSON_VALUE).content("{"
                		+"\"numeroQuestao\": 1,"
                		+"\"alternativaSelecionada\": \"C\""
                		+"}"))
        .andDo(print())
        .andDo(document("prova-controller/query",
        		requestFields(
                    	fieldWithPath("numeroQuestao").description("Número da questão").type(JsonFieldType.NUMBER),
                    	fieldWithPath("alternativaSelecionada").description("Alternativa selecionada.").type(JsonFieldType.STRING)
        				),
            responseFields(
            		fieldWithPath("numeroQuestao").description("Número da questão a ser respondida.").type(JsonFieldType.NUMBER),
            		fieldWithPath("alternativaEscolhida").description("Alternativa escolhida.").type(JsonFieldType.STRING),
            		fieldWithPath("alternativaCorreta").description("Alternativa selecionada.").type(JsonFieldType.STRING),
            		fieldWithPath("complexidade").description("Complexidade da questão.").type(JsonFieldType.STRING)
            		)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.numeroQuestao").value(1))
		.andExpect(jsonPath("$.alternativaEscolhida").value("C"))
		.andExpect(jsonPath("$.alternativaCorreta").value("C"))
		.andExpect(jsonPath("$.complexidade").value("DIFÍCIL"));
		
        verify(questaoService, times(1)).execute(any());
		
	}
	
	private List<QuestaoDTO> mockQuestaoDto(){
		return Arrays.asList(QuestaoDTO.builder()
										.enunciado("Lorem ipsulm")
										.alternativaCorreta('C')
										.nivel("DIFÍCIL")
										.numeroQuestao(1)
										.build(), 
										QuestaoDTO.builder()
										.enunciado("Lorem ipsulm")
										.alternativaCorreta('A')
										.nivel("FÁCIL")
										.numeroQuestao(2)
										.build());
	}
	
	
}

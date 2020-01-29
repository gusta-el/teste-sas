package com.teste.sas.testeSas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.teste.sas.testeSas.dto.GabaritoDTO;
import com.teste.sas.testeSas.dto.RespostaInDTO;
import com.teste.sas.testeSas.dto.RespostaOutDTO;
import com.teste.sas.testeSas.dto.RespostaQuestao;
import com.teste.sas.testeSas.service.ProvaService;
import com.teste.sas.testeSas.service.QuestaoService;

@RestController
@RequestMapping(path = "teste-sas/provas")
public class ProvaController {

	@Autowired
	ProvaService provaService;

	@Autowired
	QuestaoService questaoService;
	
	@GetMapping(value="/{id_prova}")
    @ResponseBody
    public ResponseEntity<GabaritoDTO> listarGabarito(@PathVariable("id_prova") Long idProva) {
		
		GabaritoDTO gabarito = provaService.execute(idProva);
		
		return ResponseEntity.ok(gabarito);
    }

	@PostMapping(value="/{id_prova}/responder-questao")
    @ResponseBody
    public ResponseEntity<RespostaOutDTO> responderQuestao(@PathVariable("id_prova") Long idProva,
    												  	   @RequestBody @Valid RespostaInDTO respostaInDto) {
		
		RespostaQuestao respostaQuestao = this.transform(idProva, respostaInDto);
		RespostaOutDTO respostaOutDto = questaoService.execute(respostaQuestao);
		
		return ResponseEntity.ok(respostaOutDto);
    }
	
	private RespostaQuestao transform(Long idProva, RespostaInDTO respostaInDto) {
		
		return RespostaQuestao.builder()
				.idProva(idProva)
				.numeroQuestao(respostaInDto.getNumeroQuestao())
				.alternativaSelecionada(respostaInDto.getAlternativaSelecionada())
				.build();
		
	}
	
	
}

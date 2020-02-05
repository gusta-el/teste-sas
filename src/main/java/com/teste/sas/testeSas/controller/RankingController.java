package com.teste.sas.testeSas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.teste.sas.testeSas.dto.RankingDTO;
import com.teste.sas.testeSas.service.RankingService;

@RestController
@RequestMapping(path = "teste-sas/ranking")
public class RankingController {

	@Autowired
	RankingService rankingService;
	
	@GetMapping(value="/{id_simulado}")
    @ResponseBody
    public ResponseEntity<RankingDTO> listarRanking(@PathVariable("id_simulado") Long idSimulado){
				
		RankingDTO rankingGeral = rankingService.execute(idSimulado);
		
		return ResponseEntity.ok(rankingGeral);
		
    }
	
}

package com.teste.sas.testeSas.service;

import com.teste.sas.testeSas.dto.RespostaOutDTO;
import com.teste.sas.testeSas.dto.RespostaQuestao;

public interface QuestaoService {

	RespostaOutDTO execute(RespostaQuestao resposta);
	
}

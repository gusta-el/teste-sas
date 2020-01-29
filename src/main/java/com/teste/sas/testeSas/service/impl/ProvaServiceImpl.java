package com.teste.sas.testeSas.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.sas.testeSas.dto.GabaritoDTO;
import com.teste.sas.testeSas.dto.GabaritoDTO.QuestaoDTO;
import com.teste.sas.testeSas.entity.Prova;
import com.teste.sas.testeSas.repository.ProvaRepository;
import com.teste.sas.testeSas.service.ProvaService;
import com.teste.sas.testeSas.utils.UtilsTesteSas;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProvaServiceImpl implements ProvaService{

	@Autowired
	ProvaRepository provaRepository;

	public GabaritoDTO execute(Long idProva){

		Optional<Prova> provasOp = provaRepository.findById(idProva);
		return transform(provasOp);

	}

	private GabaritoDTO transform(Optional<Prova> provaOp) {
		GabaritoDTO gabarito = new GabaritoDTO();
		
		UtilsTesteSas.assertBusinessException(provaOp.isPresent(), () -> UtilsTesteSas.PROVA_NAO_ENCONTRADA_MESSAGE);
		
		provaOp.ifPresent(prova -> {
			gabarito.setNomeProva(prova.getNomeProva());
			gabarito.setGabarito(extractQuestoes(prova));
		});
		
		
		
		return gabarito;
	}

	private List<QuestaoDTO> extractQuestoes(Prova prova) {
		return prova.getQuestoes().stream().map(questao -> QuestaoDTO.builder().nivel(questao.getComplexidade()).numeroQuestao(questao.getNumeroQuestao())
					.enunciado(questao.getEnunciado()).alternativaCorreta(questao.getAlternativaCorreta()).build()
		).sorted(Comparator.comparingInt(QuestaoDTO::getNumeroQuestao)).collect(Collectors.toList());
	}

}

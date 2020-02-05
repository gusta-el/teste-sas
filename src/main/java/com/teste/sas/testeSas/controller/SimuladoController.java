package com.teste.sas.testeSas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.sas.testeSas.dto.SimuladoDTO;
import com.teste.sas.testeSas.service.SimuladoService;

@RestController
@RequestMapping(path = "teste-sas/simulados")
public class SimuladoController {

    @Autowired
    private SimuladoService simuladoService;
    
    @GetMapping
    public ResponseEntity<List<SimuladoDTO>> listarSimulados() throws Exception {

    	List<SimuladoDTO> simulados = simuladoService.execute();
    	
        return ResponseEntity.ok(simulados);
    }
    
    @GetMapping(value="/{id_simulado}")
    public ResponseEntity<SimuladoDTO> listarSimulado(@PathVariable("id_simulado") Long idSimulado){

    	SimuladoDTO simulado = simuladoService.execute(idSimulado);
    	
    	return ResponseEntity.ok(simulado);
    }
        
}
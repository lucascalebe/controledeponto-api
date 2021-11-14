package com.iliadigital.controledeponto.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.iliadigital.controledeponto.domain.model.Momento;
import com.iliadigital.controledeponto.domain.service.BatidaService;

@RestController
@RequestMapping(value = "/batidas", produces = MediaType.APPLICATION_JSON_VALUE)
public class BatidaController {

	@Autowired
	private BatidaService batidaService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Momento> baterPonto(@RequestBody Momento momento) {
		Momento momentoSalvo = batidaService.baterPonto(momento);
		return ResponseEntity.status(HttpStatus.CREATED).body(momentoSalvo);
	} 
}

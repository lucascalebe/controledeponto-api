package com.iliadigital.controledeponto.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iliadigital.controledeponto.domain.model.Momento;
import com.iliadigital.controledeponto.domain.repository.MomentoRepository;

@Service
public class BatidaService {

	@Autowired
	private MomentoRepository momentoRepository;
	
	public Momento baterPonto(Momento momento) {
		return momentoRepository.save(momento);
	}
}

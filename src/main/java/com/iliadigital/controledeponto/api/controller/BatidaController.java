package com.iliadigital.controledeponto.api.controller;

import com.iliadigital.controledeponto.api.assembler.MomentoInputDisassembler;
import com.iliadigital.controledeponto.api.assembler.MomentoModelAssembler;
import com.iliadigital.controledeponto.api.model.MomentoModel;
import com.iliadigital.controledeponto.api.model.input.DiaBatidaInput;
import com.iliadigital.controledeponto.api.model.input.MomentoInput;
import com.iliadigital.controledeponto.domain.model.Momento;
import com.iliadigital.controledeponto.domain.service.BatidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/batidas", produces = MediaType.APPLICATION_JSON_VALUE)
public class BatidaController {

	@Autowired
	private BatidaService batidaService;

	@Autowired
	private MomentoModelAssembler momentoModelAssembler;

	@Autowired
	private MomentoInputDisassembler momentoInputDisassembler;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MomentoModel baterPonto(@RequestBody @Valid MomentoInput momentoInput) {
		Momento momento = momentoInputDisassembler.toDomain(momentoInput);
		return momentoModelAssembler.toModel(batidaService.baterPonto(momento));
	}

	@GetMapping
	public List<MomentoModel> buscarBatidasPorDia(@RequestBody @Valid DiaBatidaInput diaBatidaInput) {
		List<Momento> momentos = batidaService.getBatidasDiarias(diaBatidaInput.getData());
		return momentoModelAssembler.toCollectionModel(momentos);
	}
}

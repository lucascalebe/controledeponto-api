package com.iliadigital.controledeponto.api.controller;

import com.iliadigital.controledeponto.api.assembler.MomentoInputDisassembler;
import com.iliadigital.controledeponto.api.assembler.MomentoModelAssembler;
import com.iliadigital.controledeponto.api.model.MomentoModel;
import com.iliadigital.controledeponto.api.model.input.MomentoInput;
import com.iliadigital.controledeponto.domain.exception.NegocioException;
import com.iliadigital.controledeponto.domain.model.Momento;
import com.iliadigital.controledeponto.domain.service.BatidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
	public List<MomentoModel> buscarBatidasPorDia(@RequestParam() @NotNull String data) {
		try {
			LocalDate dataValida = LocalDate.parse(data);
			List<Momento> momentos = batidaService.getBatidasDiarias(dataValida);
			return momentoModelAssembler.toCollectionModel(momentos);
		}
		catch (DateTimeParseException e) {
			throw new NegocioException("Parâmetro de data inválido, favor informar data no formato: YY-MM-DD");
		}
	}
}

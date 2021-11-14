package com.iliadigital.controledeponto.api.controller;

import com.iliadigital.controledeponto.api.assembler.MomentoInputDisassembler;
import com.iliadigital.controledeponto.api.assembler.MomentoModelAssembler;
import com.iliadigital.controledeponto.api.model.MomentoModel;
import com.iliadigital.controledeponto.api.model.input.MomentoInput;
import com.iliadigital.controledeponto.api.openapi.controller.BatidaControllerOpenApi;
import com.iliadigital.controledeponto.domain.exception.NegocioException;
import com.iliadigital.controledeponto.domain.model.Momento;
import com.iliadigital.controledeponto.domain.service.BatidaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping(value = "/v1/batidas", produces = MediaType.APPLICATION_JSON_VALUE)
public class BatidaController implements BatidaControllerOpenApi {

	private static final Logger logger = LoggerFactory.getLogger(BatidaController.class);

	@Autowired
	private BatidaService batidaService;

	@Autowired
	private MomentoModelAssembler momentoModelAssembler;

	@Autowired
	private MomentoInputDisassembler momentoInputDisassembler;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MomentoModel baterPonto(@RequestBody @Valid MomentoInput momentoInput) {
		logger.info("Batendo ponto...");
		Momento momento = momentoInputDisassembler.toDomain(momentoInput);
		return momentoModelAssembler.toModel(batidaService.baterPonto(momento));
	}

	@GetMapping("/{batidaId}")
	public MomentoModel buscarBatidaPorId(@PathVariable Long batidaId) {
		return momentoModelAssembler.toModel(batidaService.buscarBatidaPorIdOuFalhar(batidaId));
	}

	@GetMapping
	public List<MomentoModel> buscarBatidasPorDia(@RequestParam() @NotNull String data) {
		logger.info("Buscando batidas por dia...");
		try {
			LocalDate dataValida = LocalDate.parse(data);
			List<Momento> momentos = batidaService.getBatidasDiarias(dataValida);
			return momentoModelAssembler.toCollectionModel(momentos);
		}
		catch (DateTimeParseException e) {
			throw new NegocioException("Parâmetro de data inválido, favor informar data no formato: YYYY-MM-DD");
		}
	}
}

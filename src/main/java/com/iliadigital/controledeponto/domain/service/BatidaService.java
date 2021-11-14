package com.iliadigital.controledeponto.domain.service;

import com.iliadigital.controledeponto.domain.exception.*;
import com.iliadigital.controledeponto.domain.model.Momento;
import com.iliadigital.controledeponto.domain.repository.MomentoRepository;
import com.iliadigital.controledeponto.utils.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Service
public class BatidaService {

	@Autowired
	private MomentoRepository momentoRepository;

	public List<Momento> getBatidasDiarias(LocalDate dia) {
		return momentoRepository.findAllInADay(dia.atStartOfDay(),
				DateHelper.lastHourOfDay(dia.atStartOfDay()));
	}

	public Momento buscarBatidaPorIdOuFalhar(Long id) {
		return momentoRepository.findById(id).orElseThrow(() -> new MomentoNaoEncontradoException(id));
	}

	public Momento baterPonto(Momento momento) {

		if (momento == null || momento.getDataHora() == null) throw new NullPointerException("Data hora não pode ser nulo");
		verificarHoraAtualMaiorQueAnterior(momento);
		if (ehFimDeSemana(momento)) throw new FimDeSemanaNotAllowedException("Não é permitido registrar ponto em finais de semana.");
		validaHoraAlmoco(momento);
		if (bateuMaisDe4Vezes(momento)) throw new MaximoDeHorariosPorDiaException("Podem ser registrados apenas 4 horários por dia.");

		return momentoRepository.save(momento);
	}

	private boolean ehFimDeSemana(Momento momento) {
		DayOfWeek diaDaSemana = momento.getDataHora().getDayOfWeek();
		return List.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY).contains(diaDaSemana);
	}

	private void validaHoraAlmoco(Momento momento) {
		List<Momento> batidasDoDia = getBatidasDoDia(momento);

		if (batidasDoDia.size() == 2) {
			long minutosAlmoco = Duration.between(batidasDoDia.get(1).getDataHora(),momento.getDataHora())
					.toMinutes();

			if (minutosAlmoco < 60) throw new HorarioDeAlmocoException("É necessário no mínimo uma hora de almoço");
		}
	}

	private void verificarHoraAtualMaiorQueAnterior(Momento momento) {
		List<Momento> batidasDoDia = getBatidasDoDia(momento);

		if (batidasDoDia.size() > 0) {
			batidasDoDia.forEach(batida -> {
				if (momento.getDataHora().isBefore(batida.getDataHora())) {
					throw new NegocioException("Não é permitido registrar uma data e hora anterior à última inserida.");
				}
				if (momento.getDataHora().isEqual(batida.getDataHora())) {
					throw new NegocioException("Não é permitido registrar uma data e hora já registrada anteriormente.");
				}
			});
		}
	}

	private boolean bateuMaisDe4Vezes(Momento momento) {
		return getBatidasDoDia(momento).size() == 4;
	}

	private List<Momento> getBatidasDoDia(Momento momento) {
		return momentoRepository.findAllInADay(DateHelper.firstHourOfDay(momento.getDataHora()),
				DateHelper.lastHourOfDay(momento.getDataHora()));
	}
}

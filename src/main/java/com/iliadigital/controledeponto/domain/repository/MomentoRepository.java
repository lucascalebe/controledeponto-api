package com.iliadigital.controledeponto.domain.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iliadigital.controledeponto.domain.model.Momento;

@Repository
public interface MomentoRepository extends JpaRepository<Momento, Long>{

	@Query(value = "from Momento m where m.dataHora between :dataHoraInicio and :dataHoraFim")
	List<Momento> findAllInADay(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim);
}

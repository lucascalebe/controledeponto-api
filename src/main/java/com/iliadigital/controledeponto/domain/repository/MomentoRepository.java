package com.iliadigital.controledeponto.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iliadigital.controledeponto.domain.model.Momento;

@Repository
public interface MomentoRepository extends JpaRepository<Momento, Long>{

}

package com.matheusf.mvcmf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matheusf.mvcmf.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {
		
}

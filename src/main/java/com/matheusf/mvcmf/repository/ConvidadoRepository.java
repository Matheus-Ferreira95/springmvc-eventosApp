package com.matheusf.mvcmf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matheusf.mvcmf.model.Convidado;
import com.matheusf.mvcmf.model.Evento;

public interface ConvidadoRepository extends JpaRepository<Convidado, String> {
	
	List<Convidado> findByEvento(Evento evento);

	Convidado findByRg(String rg);
	
}

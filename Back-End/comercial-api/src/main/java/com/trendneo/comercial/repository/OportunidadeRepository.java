package com.trendneo.comercial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trendneo.comercial.model.Oportunidade;

public interface OportunidadeRepository  extends JpaRepository<Oportunidade, Long> {

	Oportunidade findById(Integer id);
	
	Oportunidade findByDescricaoAndNomeProspecto(String descricao, String nomeProspecto);
	
}

package com.br.cnc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.cnc.model.Grupo;
import com.br.cnc.model.Permissao;

public interface Permissoes extends JpaRepository<Permissao, Long> {
	
	List<Permissao> findByGruposIn(Grupo grupo);

}

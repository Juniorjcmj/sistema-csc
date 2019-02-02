package com.br.cnc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.cnc.model.Grupo;
import com.br.cnc.model.Usuario;

public interface Grupos extends JpaRepository<Grupo, Long> {
	
	List<Grupo> findByUsuariosIn(Usuario usuario);

}

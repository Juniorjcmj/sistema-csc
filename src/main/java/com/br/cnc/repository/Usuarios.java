package com.br.cnc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.cnc.model.Usuario;

public interface Usuarios extends JpaRepository<Usuario, Long> {

		Usuario findByLogin(String login);
}

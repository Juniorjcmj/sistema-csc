package com.br.cnc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.cnc.model.Cliente;

@Repository
public interface Clientes extends JpaRepository<Cliente, Integer> {

	Cliente findByNomeStartingWith(String nome);

}

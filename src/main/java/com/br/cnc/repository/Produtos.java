package com.br.cnc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.cnc.model.Produto;

@Repository
public interface Produtos extends JpaRepository<Produto, Integer> {

	
	List<Produto> findByDescricaoStartingWith(String descricao);

}

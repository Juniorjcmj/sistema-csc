package com.br.cnc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.cnc.model.Produto;

@Repository
public interface Produtos extends JpaRepository<Produto, Integer> {

}

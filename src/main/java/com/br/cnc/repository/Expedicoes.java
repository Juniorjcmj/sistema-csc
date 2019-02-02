package com.br.cnc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.cnc.model.Expedicao;

@Repository
public interface Expedicoes extends JpaRepository<Expedicao, Integer> {

}

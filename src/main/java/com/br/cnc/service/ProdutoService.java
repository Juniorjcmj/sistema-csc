package com.br.cnc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.br.cnc.exception.DataIntegrityException;
import com.br.cnc.model.Produto;
import com.br.cnc.repository.Produtos;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	Produtos repo;
	
	public List<Produto> findAll() {
		List<Produto> list = repo.findAll();
		return list;
	}

	public Produto find(Integer id) throws ObjectNotFoundException {

	  Optional<Produto> obj = repo.findById(id);
	  return obj.orElseThrow(() -> new ObjectNotFoundException(
	  "Objeto não encontrado!:" + id + ", Tipo : " + Produto.class.getName()));


	}

	public void delete(Integer id)  {
		try {
			find(id);
		} catch (ObjectNotFoundException e) {
			e.printStackTrace();
		}
		try {

			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir porque há entidades relacionadas");
		}

	}

	public Produto insert(Produto obj) throws ObjectNotFoundException {
			
		return repo.save(obj);
		
	}
	/*
	 * BUSCA DE PRODUTOS COM FILTRO
	 */
	public List<Produto>produtosFiltraddos(String descricao){
		
		List<Produto>list = repo.findByDescricaoStartingWith(descricao);		
		return list;
	}
	

}

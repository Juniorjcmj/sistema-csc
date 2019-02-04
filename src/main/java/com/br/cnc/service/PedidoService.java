package com.br.cnc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.br.cnc.exception.DataIntegrityException;
import com.br.cnc.model.Pedido;
import com.br.cnc.repository.Pedidos;
import com.br.cnc.repository.Produtos;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	Pedidos repo;
	@Autowired
	Produtos produtorepo;
	/*
	 * LISTA TODOS OS PEDIDOS DO BANCO DE DADOS 
	 */
	public List<Pedido> findAll() {
		List<Pedido> list = repo.findAll();
		return list;
	}

	/*
	 * BUSCA UM PEDIDO NO BANCO DE DADOS ATRAVES DE SUA ID
	 */
	public Pedido find(Integer id) throws ObjectNotFoundException {

			Optional<Pedido> obj = repo.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado!:" + id + ", Tipo : " + Pedido.class.getName()));
	}
	/*
	 * DELETA UM PEDIDO NO BANCO DE DADOS
	 */
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
      /*
       * INSERE O PEDIDO NO BANCO DE DADOS MAS CASO ELE JÁ EXISTA FARÁ SOMENTE O UPDATE
       */
	public Pedido insert(Pedido obj) throws ObjectNotFoundException {			
		return repo.save(obj);		
	}

	public boolean buscarPeloProduto(Integer id) {
		if(repo.findByProdutos(id)== null) {
			return true;
		}else {
			return false;
		}
		
		
	}

	

	

}

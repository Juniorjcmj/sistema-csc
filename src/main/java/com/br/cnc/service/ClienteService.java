package com.br.cnc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.br.cnc.exception.DataIntegrityException;
import com.br.cnc.model.Cliente;
import com.br.cnc.repository.Clientes;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	Clientes repo;
	
	public List<Cliente> findAll() {
		List<Cliente> list = repo.findAll();
		return list;
	}

	public Cliente find(Integer id) throws ObjectNotFoundException {

			Optional<Cliente> obj = repo.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado!:" + id + ", Tipo : " + Cliente.class.getName()));


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

	public Cliente insert(Cliente obj) throws ObjectNotFoundException {
			
		return repo.save(obj);
		
	}

	public Cliente update(Cliente obj) throws ObjectNotFoundException {

		Cliente newObj = find(obj.getId());

		return repo.saveAndFlush(newObj);
	}

	public Cliente buscarPorNome(String nome) {
		Cliente obj = repo.findByNomeStartingWith(nome);
		
		return obj;
	}
 
	

}

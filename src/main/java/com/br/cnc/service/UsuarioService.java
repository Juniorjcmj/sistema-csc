package com.br.cnc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.cnc.model.Grupo;
import com.br.cnc.model.Permissao;
import com.br.cnc.model.Usuario;
import com.br.cnc.repository.Grupos;
import com.br.cnc.repository.Permissoes;
import com.br.cnc.repository.Usuarios;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	Usuarios repo;
	@Autowired 
	Grupos repoGrupo;
	@Autowired
	Permissoes repoPermissoes;
	
	
	public List<Usuario> list() {
		List<Usuario>usuarios = repo.findAll();
		return usuarios;
	}
	
	public Usuario adicionar(Usuario usuario) {		
		repo.save(usuario);		
		return usuario;		
	}
	
	public Usuario update(Usuario usuario) throws ObjectNotFoundException {
		Usuario usuarioUpdate = find(usuario.getId());
		usuarioUpdate = usuario;
		return usuarioUpdate;
	}
	public Usuario find(Long id) throws ObjectNotFoundException {

		Optional<Usuario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado!:" + id + ", Tipo : " + Usuario.class.getName()));
      }
	public List<Grupo> listGrupos() {
		List<Grupo> listgrupos = repoGrupo.findAll();
		return listgrupos;
	}
	public List<Permissao> listpermissoes() {
		List<Permissao>listpermissoes = repoPermissoes.findAll();
		return listpermissoes;
	}

	public void delete(Long id) {
		
		repo.deleteById(id);;
		
	}
	
}

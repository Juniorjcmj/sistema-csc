package com.br.cnc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.cnc.model.Cliente;
import com.br.cnc.service.ClienteService;

import javassist.tools.rmi.ObjectNotFoundException;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ClienteService service;
	
	
	@GetMapping("/novo")
	public String novo() {
		
		return "cliente/cadastro-cliente";
	}
	
	
	@GetMapping("/")
	public ModelAndView findAll(Cliente cliente) {
		
		ModelAndView mv = new ModelAndView("cliente/cadastro-cliente");
		mv.addObject("clientes", service.findAll());
		mv.addObject("cliente", cliente);
		
		return mv;
	}
	
	// daqui para baixo
	//Vai para tela de dados completos do usuario
		@GetMapping("/dados/{id}")
		public ModelAndView dados(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		    Cliente cliente = service.find(id);
			ModelAndView mv = new ModelAndView("cliente/dados-cliente");
			mv.addObject("cliente", cliente);
			
			return mv;
		}
		
		//Vai para tela de edição de oms (mesma tela de adição, contudo é enviado para a view um objeto que já existe)
		@GetMapping("/edit/{id}")
		public ModelAndView edit(@PathVariable("id") Integer id) throws ObjectNotFoundException {
			
			return findAll(service.find(id));
		}
		
		//Exclui um post por seu ID e redireciona para a tela principal
	/*	@GetMapping("/delete/{id}")
		public ModelAndView delete(@PathVariable("id") Integer id,RedirectAttributes attributes) throws ObjectNotFoundException {
			
			Cliente c = service.find(id);
			
			List<Pedido> listServico = servicoService.buscaPeloIdCliente(c);
			
			 if (listServico.isEmpty()) {
				  service.delete(id);
					attributes.addFlashAttribute("mensagem", "Dentista  excluido com sucesso !");
			}else {
				attributes.addFlashAttribute("mensagem", "Dentista não pode ser excluido, pois a serviço relacionado !");
			}
								
			return new ModelAndView("redirect:/cliente/");
		}
		*/
		//Recebe um objeto preenchido do Thymeleaf e valida 
		//Se tudo estiver ok, salva e volta para tela principal
		//Se houver erro, retorna para tela atual exibindo as mensagens de erro
		@PostMapping("/save")
		public ModelAndView save(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes) throws ObjectNotFoundException {
			
			if(result.hasErrors()) {
				return findAll(cliente);
			}
		if (cliente.getId()==null) {
			service.insert(cliente);
			attributes.addFlashAttribute("mensagem", "Cliente  salvo com sucesso !");
		} else {
			service.insert(cliente);
			attributes.addFlashAttribute("mensagem", "Cliente  Alterado com sucesso !");

		}		
					
			
			return new ModelAndView("redirect:/cliente/");
		}
}

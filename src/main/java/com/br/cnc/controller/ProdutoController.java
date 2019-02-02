package com.br.cnc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.cnc.model.Produto;
import com.br.cnc.service.ProdutoService;

import javassist.tools.rmi.ObjectNotFoundException;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	ProdutoService service;
	
	
	@RequestMapping(consumes= MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Produto> pesquisar(String descricao) {		
		return service.produtosFiltraddos(descricao);
	}
	
	
	@GetMapping("/todos")
	public ModelAndView findAll() {
		
		Produto produto = new Produto();
		ModelAndView mv = new ModelAndView("produto/cadastro-produto");
		mv.addObject("produtos", service.findAll());
		mv.addObject("produto", produto);
		
		return mv;
	}
	
	// daqui para baixo
	//Vai para tela de adição de post
		@GetMapping("/add")
		public ModelAndView add(Produto produto) {
			
			ModelAndView mv = new ModelAndView("produto/cadastro-produto");
			mv.addObject("produtos", service.findAll());
			mv.addObject("produto", produto);
			
			return mv;
		}
		
		//Vai para tela de edição de oms (mesma tela de adição, contudo é enviado para a view um objeto que já existe)
		@GetMapping("/edit/{id}")
		public ModelAndView edit(@PathVariable("id") Integer id) throws ObjectNotFoundException {
			
			return add(service.find(id));
		}
		
		//Exclui um post por seu ID e redireciona para a tela principal
	/*	@GetMapping("/delete/{id}")
		public ModelAndView delete(@PathVariable("id") Integer id,RedirectAttributes attributes) throws ObjectNotFoundException {
			
			Produto c = service.find(id);
			
			List<Pedido> listServico = servicoService.buscaPeloIdProduto(c);
			
			 if (listServico.isEmpty()) {
				  service.delete(id);
					attributes.addFlashAttribute("mensagem", "Dentista  excluido com sucesso !");
			}else {
				attributes.addFlashAttribute("mensagem", "Dentista não pode ser excluido, pois a serviço relacionado !");
			}
								
			return new ModelAndView("redirect:/produto/");
		}
		*/
		//Recebe um objeto preenchido do Thymeleaf e valida 
		//Se tudo estiver ok, salva e volta para tela principal
		//Se houver erro, retorna para tela atual exibindo as mensagens de erro
		@PostMapping("/save")
		public ModelAndView save(@Valid Produto produto, BindingResult result, RedirectAttributes attributes) throws ObjectNotFoundException {
			
			if(result.hasErrors()) {
				return add(produto);
			}
			if(produto.getId()==null) {
				service.insert(produto);
		          attributes.addFlashAttribute("mensagem", "Produto  salvo com sucesso !");	
			}else {
			   
				 service.insert(produto);
				 attributes.addFlashAttribute("mensagem", "Produto  alterado com sucesso !");
			}
				
			
			return new ModelAndView("redirect:/produto/");
		}
}

package com.br.cnc.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.cnc.model.Pedido;
import com.br.cnc.model.Produto;
import com.br.cnc.service.ClienteService;
import com.br.cnc.service.PedidoService;
import com.br.cnc.service.ProdutoService;
import com.br.cnc.session.TabelaItensSession;

import javassist.tools.rmi.ObjectNotFoundException;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	PedidoService service;
	@Autowired
	ClienteService clienteService;
	@Autowired
	ProdutoService produtoService;
	@Autowired
	TabelaItensSession tabelaItens;
	/*
	 * PEGA A STRING DIGITADA  E BUSCA NO BANCO DE DADOS
	 */
	@RequestMapping(consumes= MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Produto> pesquisar(String descricao) {		
		return produtoService.produtosFiltraddos(descricao);
	}
		
	@GetMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("pedido/cadastro-orcamento");
		mv.addObject("uuid", UUID.randomUUID());
		return mv;
	}
	/*
	 * ADICIONA ITENS NA TABELA DE ITENS
	 */
	@PostMapping("/item")
	public ModelAndView  adicionarItem(Integer idProduto, String uuid) throws ObjectNotFoundException {
		Produto produto = produtoService.find(idProduto);
		tabelaItens.adicionarItem(uuid,produto, 1);
				
		return mvTabelaItensVenda(uuid);
		
	}
	/*
	 * ALTERA A QUANTIDADE DE PRODUTOS NA TABELA DE ITEM
	 */
	@PutMapping("/item/{idProduto}")
	public ModelAndView alterarQuantidadeItem(@PathVariable Integer idProduto, Integer quantidade,String uuid) throws ObjectNotFoundException {
		Produto produto = produtoService.find(idProduto);
		tabelaItens.alterarQuantidadeItens(uuid,produto, quantidade);
		return mvTabelaItensVenda(uuid);
	}
	/*
	 * EXCLUI PRODUTOS DA TABELA ITENS
	 */
	@DeleteMapping("/item/{uuid}/{idProduto}")
	public ModelAndView excluirItem(@PathVariable Integer idProduto, @PathVariable String uuid) throws ObjectNotFoundException {
		Produto produto = produtoService.find(idProduto);
		System.out.println("número Produto " + produto.getDescricao());
		tabelaItens.excluirItem(uuid,produto);
		
		return mvTabelaItensVenda(uuid);
	}

	private ModelAndView mvTabelaItensVenda(String uuid) {
		ModelAndView mv = new ModelAndView("pedido/TabelaItensVenda");
		mv.addObject("itens",tabelaItens.getItens(uuid));
		mv.addObject("valorTotal", tabelaItens.getValorTotal(uuid));
		return mv;
	}
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * ADICIONA UM CLIENTE A UM DETERMINADO PEDIDO
	 */
	@GetMapping("/escolherCliente")
	public ModelAndView escolherClientePedido() {
		
		Pedido pedido = new Pedido();
		ModelAndView mv = new ModelAndView("pedido/cadastro-cliente-pedido");
		mv.addObject("clientes", clienteService.findAll());	
		mv.addObject("pedido", pedido);
		return mv;
	}
	/*
	 * ADIOCIONA UM USUARIO AO PEDIDO E RETORNA A PAGINA DE ADICAO DE PRODUTOS
	 */
	@PostMapping("/addCliente")
	public ModelAndView adicionandoClientePedido(@Valid Pedido pedido, BindingResult result, RedirectAttributes attributes) throws ObjectNotFoundException {
			
			 service.insert(pedido);
			 attributes.addFlashAttribute("mensagem", "Cliente  adicionado com sucesso !");				
		     return escolherProdutoPedido(pedido);
	}
	/*
	 * MOSTRA A TELA DE ADICIONAR PRODUTOS
	 */
	@GetMapping("/escolherProduto")
	public ModelAndView escolherProdutoPedido(Pedido pedido) {		
		
		ModelAndView mv = new ModelAndView("pedido/cadastro-produto-pedido");
		mv.addObject("produtos", produtoService.findAll());	
		mv.addObject("pedido", pedido);
		return mv;
	}
	/*
	 * ADICIONA PRODUTO NO PEDIDO E RETORNA PARA PAGINA DE ADICIONAR PRODUTO
	 */
	@PostMapping("/addProduto")
	public ModelAndView adicionandoProdutosPedido(@Valid Pedido pedido, BindingResult result, RedirectAttributes attributes) throws ObjectNotFoundException {	
		
		return null;
	}
	
	
	
	
	@GetMapping("/")
	public ModelAndView findAll() {
		
		Pedido pedido = new Pedido();
		ModelAndView mv = new ModelAndView("pedido/cadastro-pedido");
		mv.addObject("pedidos", service.findAll());
		mv.addObject("pedido", pedido);
		
		return mv;
	}
	
	// daqui para baixo
	//Vai para tela de adição de post
		@GetMapping("/add")
		public ModelAndView add(Pedido pedido) {
			
			ModelAndView mv = new ModelAndView("pedido/cadastro-pedido");
			mv.addObject("pedidos", service.findAll());
			mv.addObject("pedido", pedido);
			
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
			
			Pedido c = service.find(id);
			
			List<Pedido> listServico = servicoService.buscaPeloIdPedido(c);
			
			 if (listServico.isEmpty()) {
				  service.delete(id);
					attributes.addFlashAttribute("mensagem", "Dentista  excluido com sucesso !");
			}else {
				attributes.addFlashAttribute("mensagem", "Dentista não pode ser excluido, pois a serviço relacionado !");
			}
								
			return new ModelAndView("redirect:/pedido/");
		}
		*/
		//Recebe um objeto preenchido do Thymeleaf e valida 
		//Se tudo estiver ok, salva e volta para tela principal
		//Se houver erro, retorna para tela atual exibindo as mensagens de erro
		@PostMapping("/save")
		public ModelAndView save(@Valid Pedido pedido, BindingResult result, RedirectAttributes attributes) throws ObjectNotFoundException {
			
			if(result.hasErrors()) {
				return add(pedido);
			}
			if(pedido.getId()==null) {
				service.insert(pedido);
		          attributes.addFlashAttribute("mensagem", "Pedido  salvo com sucesso !");	
			}else {
			   
				 service.insert(pedido);
				 attributes.addFlashAttribute("mensagem", "Pedido  alterado com sucesso !");
			}
				
			
			return new ModelAndView("redirect:/pedido/");
		}
}

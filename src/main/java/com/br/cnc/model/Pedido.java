package com.br.cnc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Date dataOrcamento;
	private Date dataEntrega;

	private BigDecimal valorTotal;
	private BigDecimal valorFrete;
	private BigDecimal valorDesconto;
	private BigDecimal qntd;

	private String status;
	private String vendendor;
	private String cliente;
	private String observacao;
	private String enderecoEntrega;

	@ManyToOne
	private Usuario usuario;

	@ManyToMany
	private List<Produto> produtos;

	public Pedido() {
		// TODO Auto-generated constructor stub
	}

	public Pedido(Integer id, Date dataOrcamento, Date dataEntrega, BigDecimal valorTotal, BigDecimal valorFrete,
			BigDecimal valorDesconto, BigDecimal qntd, String status, String vendendor, String cliente,
			String observacao, String enderecoEntrega, Usuario usuario) {
		super();
		this.id = id;
		this.dataOrcamento = dataOrcamento;
		this.dataEntrega = dataEntrega;
		this.valorTotal = valorTotal;
		this.valorFrete = valorFrete;
		this.valorDesconto = valorDesconto;
		this.qntd = qntd;
		this.status = status;
		this.vendendor = vendendor;
		this.cliente = cliente;
		this.observacao = observacao;
		this.enderecoEntrega = enderecoEntrega;
		this.usuario = usuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataOrcamento() {
		return dataOrcamento;
	}

	public void setDataOrcamento(Date dataOrcamento) {
		this.dataOrcamento = dataOrcamento;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getVendendor() {
		return vendendor;
	}

	public void setVendendor(String vendendor) {
		this.vendendor = vendendor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public BigDecimal getQntd() {
		return qntd;
	}

	public void setQntd(BigDecimal qntd) {
		this.qntd = qntd;
	}

	public BigDecimal getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(String enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

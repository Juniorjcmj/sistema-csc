package com.br.cnc.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	
	private String descricao;
	
	private BigDecimal valor;
	
	private BigDecimal qntd;

	public Produto(Integer id, String descricao, BigDecimal valor,BigDecimal qntd) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.qntd = qntd;
	}
	
	
	public Produto() {
		// TODO Auto-generated constructor stub
	}
	
	


	public BigDecimal getQntd() {
		return qntd;
	}


	public void setQntd(BigDecimal qntd) {
		this.qntd = qntd;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public BigDecimal getValor() {
		return valor;
	}


	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	
}

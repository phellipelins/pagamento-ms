package com.estudo.pagamento.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_pagamento")
public class Pagamento implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private BigDecimal valor;
	@Column(length = 80)
	private String nome;
	private String numeroCartao; 
	private LocalDate expiracaoCartao;
	private String codigo;
	@Enumerated(EnumType.STRING)
	private PagamentoStatus status;
	private Long carrinhoId;
	
	public Pagamento(
		Long id, BigDecimal valor, String nome, String numeroCartao,
		LocalDate expiracaoCartao, String codigo, PagamentoStatus status,
		Long carrinhoId
	) {
		super();
		this.id = id;
		this.valor = valor;
		this.nome = nome;
		this.numeroCartao = numeroCartao;
		this.expiracaoCartao = expiracaoCartao;
		this.codigo = codigo;
		this.status = status;
		this.carrinhoId = carrinhoId;
	}
	
	public Pagamento() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public LocalDate getExpiracaoCartao() {
		return expiracaoCartao;
	}

	public void setExpiracaoCartao(LocalDate expiracaoCartao) {
		this.expiracaoCartao = expiracaoCartao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public PagamentoStatus getStatus() {
		return status;
	}

	public void setStatus(PagamentoStatus status) {
		this.status = status;
	}

	public Long getCarrinhoId() {
		return carrinhoId;
	}

	public void setCarrinhoId(Long carrinhoId) {
		this.carrinhoId = carrinhoId;
	}
}

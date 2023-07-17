package com.estudo.pagamento.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.estudo.pagamento.entities.PagamentoStatus;

public class PagamentoDto {
	private Long id;
	private BigDecimal valor;
	private String nome;
	private String numeroCartao; 
	private LocalDate expiracaoCartao;
	private String codigo;
	private PagamentoStatus status;
	private Long carrinhoId;

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

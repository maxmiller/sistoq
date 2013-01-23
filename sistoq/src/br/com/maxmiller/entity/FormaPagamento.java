package br.com.maxmiller.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Forma_Pagamento database table.
 * 
 */
@Entity
@Table(name="Forma_Pagamento")
public class FormaPagamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="forma_pagamento_id")
	private int formaPagamentoId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date alteracao;

	@Temporal(TemporalType.TIMESTAMP)
	private Date criacao;

	@Column(name="desconto_permitido")
	private BigDecimal descontoPermitido;

	private byte inativo;

	private String nome;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;

	//bi-directional many-to-one association to Venda
	@OneToMany(mappedBy="formaPagamento")
	private List<Venda> vendas;

	public FormaPagamento() {
	}

	public int getFormaPagamentoId() {
		return this.formaPagamentoId;
	}

	public void setFormaPagamentoId(int formaPagamentoId) {
		this.formaPagamentoId = formaPagamentoId;
	}

	public Date getAlteracao() {
		return this.alteracao;
	}

	public void setAlteracao(Date alteracao) {
		this.alteracao = alteracao;
	}

	public Date getCriacao() {
		return this.criacao;
	}

	public void setCriacao(Date criacao) {
		this.criacao = criacao;
	}

	public BigDecimal getDescontoPermitido() {
		return this.descontoPermitido;
	}

	public void setDescontoPermitido(BigDecimal descontoPermitido) {
		this.descontoPermitido = descontoPermitido;
	}

	public byte getInativo() {
		return this.inativo;
	}

	public void setInativo(byte inativo) {
		this.inativo = inativo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Venda> getVendas() {
		return this.vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

}
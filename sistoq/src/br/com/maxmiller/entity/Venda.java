package br.com.maxmiller.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the venda database table.
 * 
 */
@Entity
public class Venda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="venda_id")
	private int vendaId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	private BigDecimal desconto;

	@Column(name="desconto_valor")
	private BigDecimal descontoValor;

	private String observacao;

	private BigDecimal total;

	//bi-directional many-to-one association to ItemVenda
	@OneToMany(mappedBy="venda")
	private List<ItemVenda> itemVendas;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;

	//bi-directional many-to-one association to FormaPagamento
	@ManyToOne
	@JoinColumn(name="forma_pagamento_id")
	private FormaPagamento formaPagamento;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;

	public Venda() {
	}

	public int getVendaId() {
		return this.vendaId;
	}

	public void setVendaId(int vendaId) {
		this.vendaId = vendaId;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public BigDecimal getDesconto() {
		return this.desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public BigDecimal getDescontoValor() {
		return this.descontoValor;
	}

	public void setDescontoValor(BigDecimal descontoValor) {
		this.descontoValor = descontoValor;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<ItemVenda> getItemVendas() {
		return this.itemVendas;
	}

	public void setItemVendas(List<ItemVenda> itemVendas) {
		this.itemVendas = itemVendas;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public FormaPagamento getFormaPagamento() {
		return this.formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
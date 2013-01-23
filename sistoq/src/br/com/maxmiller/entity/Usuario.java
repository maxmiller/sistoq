package br.com.maxmiller.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Usuario database table.
 * 
 */
@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="usuario_id")
	private int usuarioId;

	private byte administrador;

	@Temporal(TemporalType.TIMESTAMP)
	private Date alteracao;

	@Temporal(TemporalType.TIMESTAMP)
	private Date criacao;

	private byte inativo;

	private String login;

	private String nome;

	private String senha;

	//bi-directional many-to-one association to Cliente
	@OneToMany(mappedBy="usuario")
	private List<Cliente> clientes;

	//bi-directional many-to-one association to FormaPagamento
	@OneToMany(mappedBy="usuario")
	private List<FormaPagamento> formaPagamentos;

	//bi-directional many-to-one association to Produto
	@OneToMany(mappedBy="usuario")
	private List<Produto> produtos;

	//bi-directional many-to-one association to EntradaEstoque
	@OneToMany(mappedBy="usuario")
	private List<EntradaEstoque> entradaEstoques;

	//bi-directional many-to-one association to Fornecedor
	@OneToMany(mappedBy="usuario")
	private List<Fornecedor> fornecedors;

	//bi-directional many-to-one association to MotivoEntradaEstoque
	@OneToMany(mappedBy="usuario")
	private List<MotivoEntradaEstoque> motivoEntradaEstoques;

	//bi-directional many-to-one association to MotivoSaidaEstoque
	@OneToMany(mappedBy="usuario")
	private List<MotivoSaidaEstoque> motivoSaidaEstoques;

	//bi-directional many-to-one association to SaidaEstoque
	@OneToMany(mappedBy="usuario")
	private List<SaidaEstoque> saidaEstoques;

	//bi-directional many-to-one association to Venda
	@OneToMany(mappedBy="usuario")
	private List<Venda> vendas;

	public Usuario() {
	}

	public int getUsuarioId() {
		return this.usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public byte getAdministrador() {
		return this.administrador;
	}

	public void setAdministrador(byte administrador) {
		this.administrador = administrador;
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

	public byte getInativo() {
		return this.inativo;
	}

	public void setInativo(byte inativo) {
		this.inativo = inativo;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Cliente> getClientes() {
		return this.clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<FormaPagamento> getFormaPagamentos() {
		return this.formaPagamentos;
	}

	public void setFormaPagamentos(List<FormaPagamento> formaPagamentos) {
		this.formaPagamentos = formaPagamentos;
	}

	public List<Produto> getProdutos() {
		return this.produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<EntradaEstoque> getEntradaEstoques() {
		return this.entradaEstoques;
	}

	public void setEntradaEstoques(List<EntradaEstoque> entradaEstoques) {
		this.entradaEstoques = entradaEstoques;
	}

	public List<Fornecedor> getFornecedors() {
		return this.fornecedors;
	}

	public void setFornecedors(List<Fornecedor> fornecedors) {
		this.fornecedors = fornecedors;
	}

	public List<MotivoEntradaEstoque> getMotivoEntradaEstoques() {
		return this.motivoEntradaEstoques;
	}

	public void setMotivoEntradaEstoques(List<MotivoEntradaEstoque> motivoEntradaEstoques) {
		this.motivoEntradaEstoques = motivoEntradaEstoques;
	}

	public List<MotivoSaidaEstoque> getMotivoSaidaEstoques() {
		return this.motivoSaidaEstoques;
	}

	public void setMotivoSaidaEstoques(List<MotivoSaidaEstoque> motivoSaidaEstoques) {
		this.motivoSaidaEstoques = motivoSaidaEstoques;
	}

	public List<SaidaEstoque> getSaidaEstoques() {
		return this.saidaEstoques;
	}

	public void setSaidaEstoques(List<SaidaEstoque> saidaEstoques) {
		this.saidaEstoques = saidaEstoques;
	}

	public List<Venda> getVendas() {
		return this.vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

}
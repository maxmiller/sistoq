package br.com.maxmiller.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the fornecedor database table.
 * 
 */
@Entity
public class Fornecedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="fornecedor_id")
	private int fornecedorId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date alteracao;

	private String cnpj;

	private String cpf;

	@Temporal(TemporalType.TIMESTAMP)
	private Date criacao;

	private String endereco;

	private byte inativo;

	private String nome;

	@Column(name="telefone_celular")
	private String telefoneCelular;

	@Column(name="telefone_fixo")
	private String telefoneFixo;

	//bi-directional many-to-one association to Produto
	@OneToMany(mappedBy="fornecedor")
	private List<Produto> produtos;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;

	public Fornecedor() {
	}

	public int getFornecedorId() {
		return this.fornecedorId;
	}

	public void setFornecedorId(int fornecedorId) {
		this.fornecedorId = fornecedorId;
	}

	public Date getAlteracao() {
		return this.alteracao;
	}

	public void setAlteracao(Date alteracao) {
		this.alteracao = alteracao;
	}

	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getCriacao() {
		return this.criacao;
	}

	public void setCriacao(Date criacao) {
		this.criacao = criacao;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
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

	public String getTelefoneCelular() {
		return this.telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public String getTelefoneFixo() {
		return this.telefoneFixo;
	}

	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}

	public List<Produto> getProdutos() {
		return this.produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
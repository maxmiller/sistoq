package br.com.maxmiller.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Produto database table.
 * 
 */
@Entity
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="produto_id")
	private int produtoId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date alteracao;

	private String codigo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date criacao;

	private byte inativo;

	private String nome;

	@Column(name="preco_custo")
	private BigDecimal precoCusto;

	@Column(name="preco_venda")
	private BigDecimal precoVenda;

	private int quantidade;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;

	//bi-directional many-to-one association to Fornecedor
	@ManyToOne
	@JoinColumn(name="fornecedor_id")
	private Fornecedor fornecedor;

	//bi-directional many-to-one association to EntradaEstoque
	@OneToMany(mappedBy="produto")
	private List<EntradaEstoque> entradaEstoques;

	//bi-directional many-to-one association to ItemVenda
	@OneToMany(mappedBy="produto")
	private List<ItemVenda> itemVendas;

	//bi-directional many-to-one association to SaidaEstoque
	@OneToMany(mappedBy="produto")
	private List<SaidaEstoque> saidaEstoques;

	public Produto() {
	}

	public int getProdutoId() {
		return this.produtoId;
	}

	public void setProdutoId(int produtoId) {
		this.produtoId = produtoId;
	}

	public Date getAlteracao() {
		return this.alteracao;
	}

	public void setAlteracao(Date alteracao) {
		this.alteracao = alteracao;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPrecoCusto() {
		return this.precoCusto;
	}

	public void setPrecoCusto(BigDecimal precoCusto) {
		this.precoCusto = precoCusto;
	}

	public BigDecimal getPrecoVenda() {
		return this.precoVenda;
	}

	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}

	public int getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Fornecedor getFornecedor() {
		return this.fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<EntradaEstoque> getEntradaEstoques() {
		return this.entradaEstoques;
	}

	public void setEntradaEstoques(List<EntradaEstoque> entradaEstoques) {
		this.entradaEstoques = entradaEstoques;
	}

	public List<ItemVenda> getItemVendas() {
		return this.itemVendas;
	}

	public void setItemVendas(List<ItemVenda> itemVendas) {
		this.itemVendas = itemVendas;
	}

	public List<SaidaEstoque> getSaidaEstoques() {
		return this.saidaEstoques;
	}

	public void setSaidaEstoques(List<SaidaEstoque> saidaEstoques) {
		this.saidaEstoques = saidaEstoques;
	}

}
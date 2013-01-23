package br.com.maxmiller.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the saida_estoque database table.
 * 
 */
@Entity
@Table(name="saida_estoque")
public class SaidaEstoque implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="saida_estoque_id")
	private int saidaEstoqueId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date alteracao;

	@Temporal(TemporalType.TIMESTAMP)
	private Date criacao;

	private byte inativo;

	@Column(name="preco_custo")
	private BigDecimal precoCusto;

	@Column(name="preco_venda")
	private BigDecimal precoVenda;

	private int quantidade;

	//bi-directional many-to-one association to Produto
	@ManyToOne
	@JoinColumn(name="produto_id")
	private Produto produto;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;

	//bi-directional many-to-one association to MotivoSaidaEstoque
	@ManyToOne
	@JoinColumn(name="motivo_saida_estoque_id")
	private MotivoSaidaEstoque motivoSaidaEstoque;

	public SaidaEstoque() {
	}

	public int getSaidaEstoqueId() {
		return this.saidaEstoqueId;
	}

	public void setSaidaEstoqueId(int saidaEstoqueId) {
		this.saidaEstoqueId = saidaEstoqueId;
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

	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public MotivoSaidaEstoque getMotivoSaidaEstoque() {
		return this.motivoSaidaEstoque;
	}

	public void setMotivoSaidaEstoque(MotivoSaidaEstoque motivoSaidaEstoque) {
		this.motivoSaidaEstoque = motivoSaidaEstoque;
	}

}
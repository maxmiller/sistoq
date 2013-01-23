package br.com.maxmiller.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the item_venda database table.
 * 
 */
@Entity
@Table(name="item_venda")
public class ItemVenda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="item_venda_id")
	private int itemVendaId;

	private BigDecimal desconto;

	@Column(name="preco_final")
	private BigDecimal precoFinal;

	@Column(name="preco_venda")
	private BigDecimal precoVenda;

	//bi-directional many-to-one association to Produto
	@ManyToOne
	@JoinColumn(name="produto_id")
	private Produto produto;

	//bi-directional many-to-one association to Venda
	@ManyToOne
	@JoinColumn(name="venda_id")
	private Venda venda;

	public ItemVenda() {
	}

	public int getItemVendaId() {
		return this.itemVendaId;
	}

	public void setItemVendaId(int itemVendaId) {
		this.itemVendaId = itemVendaId;
	}

	public BigDecimal getDesconto() {
		return this.desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public BigDecimal getPrecoFinal() {
		return this.precoFinal;
	}

	public void setPrecoFinal(BigDecimal precoFinal) {
		this.precoFinal = precoFinal;
	}

	public BigDecimal getPrecoVenda() {
		return this.precoVenda;
	}

	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Venda getVenda() {
		return this.venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

}
package br.com.maxmiller.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the motivo_entrada_estoque database table.
 * 
 */
@Entity
@Table(name="motivo_entrada_estoque")
public class MotivoEntradaEstoque implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="motivo_entrada_estoque_id")
	private int motivoEntradaEstoqueId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date alteracao;

	@Temporal(TemporalType.TIMESTAMP)
	private Date criacao;

	private byte inativo;

	private String nome;

	//bi-directional many-to-one association to EntradaEstoque
	@OneToMany(mappedBy="motivoEntradaEstoque")
	private List<EntradaEstoque> entradaEstoques;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;

	public MotivoEntradaEstoque() {
	}

	public int getMotivoEntradaEstoqueId() {
		return this.motivoEntradaEstoqueId;
	}

	public void setMotivoEntradaEstoqueId(int motivoEntradaEstoqueId) {
		this.motivoEntradaEstoqueId = motivoEntradaEstoqueId;
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

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<EntradaEstoque> getEntradaEstoques() {
		return this.entradaEstoques;
	}

	public void setEntradaEstoques(List<EntradaEstoque> entradaEstoques) {
		this.entradaEstoques = entradaEstoques;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
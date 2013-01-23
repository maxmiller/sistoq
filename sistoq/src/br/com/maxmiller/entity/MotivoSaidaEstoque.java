package br.com.maxmiller.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the motivo_saida_estoque database table.
 * 
 */
@Entity
@Table(name="motivo_saida_estoque")
public class MotivoSaidaEstoque implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="motivo_saida_estoque_id")
	private int motivoSaidaEstoqueId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date alteracao;

	@Temporal(TemporalType.TIMESTAMP)
	private Date criacao;

	private byte inativo;

	private String nome;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;

	//bi-directional many-to-one association to SaidaEstoque
	@OneToMany(mappedBy="motivoSaidaEstoque")
	private List<SaidaEstoque> saidaEstoques;

	public MotivoSaidaEstoque() {
	}

	public int getMotivoSaidaEstoqueId() {
		return this.motivoSaidaEstoqueId;
	}

	public void setMotivoSaidaEstoqueId(int motivoSaidaEstoqueId) {
		this.motivoSaidaEstoqueId = motivoSaidaEstoqueId;
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

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<SaidaEstoque> getSaidaEstoques() {
		return this.saidaEstoques;
	}

	public void setSaidaEstoques(List<SaidaEstoque> saidaEstoques) {
		this.saidaEstoques = saidaEstoques;
	}

}
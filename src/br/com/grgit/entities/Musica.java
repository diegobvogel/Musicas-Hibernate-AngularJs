package br.com.grgit.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MUSICAS")
public class Musica implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private int codigo;
	private String nome;
	private String banda;
	private String duracao;

	public Musica() {
	}

	public Musica(int codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}
	
	public Musica(int codigo, String nome, String banda, String duracao) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.banda = banda;
		this.duracao = duracao;
	}

	@Id
	@GeneratedValue
	@Column(name = "CODIGO", unique = true, nullable = false, precision = 20, scale = 0)
	public int getCodigo() {
		return this.codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	@Column(name = "NOME", nullable = true)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "BANDA", nullable = false)
	public String getBanda() {
		return banda;
	}

	public void setBanda(String banda) {
		this.banda = banda;
	}

	@Column(name = "DURACAO", nullable = false)
	public String getDuracao() {
		return duracao;
	}

	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}

  }

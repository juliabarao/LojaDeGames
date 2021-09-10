package br.org.generation.lojadegames.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_produtos")
public class Produtos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message = "O atributo nome é obrigatório, por se tratar do nome do produto!")
	@Size(min = 5, max = 100, message = "O atributo nome deve ter no mínimo 5 e no máximo 100 caracteres!")
	private String nome;
	
	@NotNull(message = "O atributo quantidade é obrigatório!")
	private int quantidade;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data = new java.sql.Date(System.currentTimeMillis());
	
	@Column(columnDefinition = "integer default 0")
	private int curtidos;
	
	@ManyToOne
	@JsonIgnoreProperties("produtos")
	private Categoria categoria;
	
	@ManyToOne
	@JsonIgnoreProperties("produtos")
	private Usuarios usuarios;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	public int getCurtidos() {
		return curtidos;
	}

	public void setCurtidos(int curtidos) {
		this.curtidos = curtidos;
	}

}
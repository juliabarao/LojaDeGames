package br.org.generation.lojadegames.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_usuarioslg")
public class Usuarios {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message = "O atributo Nome é obrigatório!")
	private String nome;
	
	@NotNull(message = "O atributo Usuário é obrigatório!")
	private String usuarios;
	
	@NotNull(message = "O atributo Senha é obrigatório!")
	@Size(min = 8, message = "A Sanha deve ter no mínimo 8 caracteres!")
	private String senha;
	
	@Column(name = "data_nascimento")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "O atributo Data de Nascimento é Obrigatório!")
	private LocalDate dataNascimento;
	
	@OneToMany(mappedBy = "usuarios", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuarios")
	private List<Produtos> produto;
	
	public Usuarios(long id, String nome, String usuarios, String senha, LocalDate dataNascimento) {
		this.id = id;
		this.nome = nome;
		this.usuarios = usuarios;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
	}

	public Usuarios() {	}

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

	public String getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(String usuarios) {
		this.usuarios = usuarios;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Produtos> getProduto() {
		return produto;
	}

	public void setProduto(List<Produtos> produto) {
		this.produto = produto;
	}
}
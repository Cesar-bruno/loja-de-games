package com.generation.lojadegames.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity // defini que vai ser uma tabela
@Table(name = "tb_produto") // defini o nome da tabela 

public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // configura que vai auto incremento
	private long id ;
	
	@Column(length = 20)
	@NotBlank(message = " O atributo nome é obrigatório.") // defini que o nome n pode ficar vazia, e msg que vai ser visualizada
	@Size(min = 5, max = 20, message = "O atributo Titulo precisa ter entre 5 e 20 caracteres.") // defini o tamanho do titulo
	private String nome;
	
	
	@NotBlank(message = " O atributo valor é obrigatório.") // defini que o valor n pode ficar vazia, e msg que vai ser visualizada
	private BigDecimal preco;


	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Categoria categoria;

	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Usuario usuario;
	
	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


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


	public BigDecimal getPreco() {
		return preco;
	}


	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	


	
	
}

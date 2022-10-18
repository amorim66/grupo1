package com.fatec.group1.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;



@Entity
public class Produto {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    @NotBlank(message = "Descricao é requerida.")
	private String descricao;

    @NotNull(message = "Valor é requerido.")
	private Double valorVenda;

    @NotBlank(message = "Categoria é requerida.")
	private String categoria;

	private String marca;

    @NotNull(message = "Informe a quantidade em estoque!")
	private Double quantidadeEstoque=0.;
    
    public Produto(String descricao, Double valorVenda, String categoria, String marca, Double quantidadeEstoque) {
        this.descricao = descricao;
        this.valorVenda = valorVenda;
        this.categoria = categoria;
        this.marca = marca;
        this.quantidadeEstoque = quantidadeEstoque;
    }
    
    
    
	public Produto(Long id, String descricao, Double valorVenda ,Double quantidadeEstoque) {
		this.id = id;
		this.descricao = descricao;
		this.valorVenda = valorVenda;
		this.quantidadeEstoque = quantidadeEstoque;
	}



	public Produto() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Double getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Double quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}
    
    

}

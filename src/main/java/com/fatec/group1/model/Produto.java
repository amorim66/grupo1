package com.fatec.group1.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
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

}

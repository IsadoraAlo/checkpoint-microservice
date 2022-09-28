package br.com.fiap.appprodutoteste.produto.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document ("tb_produto")
@Data
public class Produto {


	private String id;
	private String nome;
	private Integer quantidade; 
	private BigDecimal valor;
	
	public Produto() {
	}
	
	public Produto(String id, String nome, Integer quantidade, BigDecimal valor) {
		this.id = id;
		this.nome = nome;
		this.quantidade = quantidade;
		this.valor = valor;
	}

	public String toString(){
		return "PRODUTO:  NOME: " + nome + " QUANTIDADE: " + quantidade + " VALOR: " + valor;
	}


}

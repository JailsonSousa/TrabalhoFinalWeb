package com.ufc.br.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Prato {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigo;
	private String nome;
	private double valor;
	private String imagem;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public String getimagem() {
		return imagem;
	}
	
	public void setPathImg(String imagem) {
		this.imagem = imagem;
	}
}

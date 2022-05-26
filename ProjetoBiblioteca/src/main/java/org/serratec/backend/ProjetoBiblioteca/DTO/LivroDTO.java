package org.serratec.backend.ProjetoBiblioteca.DTO;

import java.util.Date;

public class LivroDTO {

	private Integer idLivro;
	private String titulo;
	private String tipo;
	private String autor;
	private Date dataPublicacao;
	
	
	public LivroDTO() {}


	public Integer getIdLivro() {
		return idLivro;
	}


	public void setIdLivro(Integer idLivro) {
		this.idLivro = idLivro;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getAutor() {
		return autor;
	}


	public void setAutor(String autor) {
		this.autor = autor;
	}


	public Date getDataPublicacao() {
		return dataPublicacao;
	}


	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}


}


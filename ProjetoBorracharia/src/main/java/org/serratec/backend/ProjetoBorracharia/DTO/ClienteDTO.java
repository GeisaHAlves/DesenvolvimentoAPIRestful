package org.serratec.backend.ProjetoBorracharia.DTO;

import java.io.Serializable;

public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = -486519720402196955L;

	private Integer idCliente;
	private String nome;
	private String cpf;
	private String email;
	private String numeroTelefone;

	public ClienteDTO() {

	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumeroTelefone() {
		return numeroTelefone;
	}

	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

}

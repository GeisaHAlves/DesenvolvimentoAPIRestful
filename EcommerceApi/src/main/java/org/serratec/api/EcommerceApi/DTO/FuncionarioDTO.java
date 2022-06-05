package org.serratec.api.EcommerceApi.DTO;

import java.io.Serializable;

public class FuncionarioDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer idFuncionario;
	private String nome;
	private String cpf;
	
	public FuncionarioDTO() {
		super();
	}

	public Integer getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Integer idFuncionario) {
		this.idFuncionario = idFuncionario;
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
	
	
}

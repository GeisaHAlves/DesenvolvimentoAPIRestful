package org.serratec.backend.SistemaBancario.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "conta_id")
	private Integer idConta;

	@Column(name = "conta_numero")
	private String numero;

	@Column(name = "conta_titular")
	private String titular;

	@Column(name = "conta_saldo")
	private Double saldo;

	public Conta() {
	}

	public Conta(Integer idConta, String numero, String titular, Double saldo) {
		this.idConta = idConta;
		this.numero = numero;
		this.titular = titular;
		this.saldo = saldo;
	}

	public Integer getIdConta() {
		return idConta;
	}

	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void sacar(Double valor) {
		saldo -= valor;
	}

	public void depositar(Double valor) {
		saldo += valor;
	}
	
}

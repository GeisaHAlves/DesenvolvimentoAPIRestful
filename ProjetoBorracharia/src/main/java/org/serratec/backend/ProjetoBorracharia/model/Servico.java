package org.serratec.backend.ProjetoBorracharia.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "servico")
public class Servico implements Serializable {

	private static final long serialVersionUID = 7670099952484976067L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "servico_cd_id")
	private Integer idServico;

	@Column(name = "servico_tx_servico_prestado")
	private String servicoPrestado;

	@Column(name = "servico_dt_data_servico")
	private LocalDate dataServico;

	@Column(name = "servico_db_valor")
	private Double valor;
	

	@ManyToOne
	@JoinColumn(name = "carro_id", referencedColumnName = "carro_cd_id")
	@JsonIgnore
	private Carro carro;

	public Servico() {
	}

	public Integer getIdServico() {
		return idServico;
	}

	public void setIdServico(Integer idServico) {
		this.idServico = idServico;
	}

	public String getServicoPrestado() {
		return servicoPrestado;
	}

	public void setServicoPrestado(String servicoPrestado) {
		this.servicoPrestado = servicoPrestado;
	}

	public LocalDate getDataServico() {
		return dataServico;
	}

	public void setDataServico(LocalDate dataServico) {
		this.dataServico = dataServico;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

}
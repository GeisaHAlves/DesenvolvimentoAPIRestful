package org.serratec.backend.ProjetoBorracharia.DTO;

import java.io.Serializable;
import java.time.LocalDate;

public class ServicoDTO implements Serializable {

	private static final long serialVersionUID = -486519720402196955L;

	private Integer idServico;
	private String servicoPrestado;
	private LocalDate dataServico;
	private Double valor;
	private Integer idCarro;

	public ServicoDTO() {

	}

	public Integer getIdServico() {
		return idServico;
	}

	public void setIdServico(Integer idServico) {
		this.idServico = idServico;
	}

	public Integer getIdCarro() {
		return idCarro;
	}

	public void setIdCarro(Integer idCarro) {
		this.idCarro = idCarro;
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

}

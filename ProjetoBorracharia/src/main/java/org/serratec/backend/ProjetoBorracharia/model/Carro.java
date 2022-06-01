package org.serratec.backend.ProjetoBorracharia.model;

import java.io.Serializable;

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
	@Table(name = "carro")
	public class Carro implements Serializable{

		private static final long serialVersionUID = 7670099952484976067L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "carro_cd_id")
		private Integer idCarro;

		@Column(name = "carro_tx_modelo")
		private String modelo;

		
		@Column(name = "carro_tx_marca")
		private String marca;

		@Column(name = "carro_tx_ano")
		private String ano;

		@ManyToOne
		@JoinColumn(name="cliente_id", referencedColumnName = "cliente_cd_id")
		@JsonIgnore
		private Cliente cliente;

		public Carro() {
		}

		public Integer getIdCarro() {
			return idCarro;
		}

		public void setIdCarro(Integer idCarro) {
			this.idCarro = idCarro;
		}

		public String getModelo() {
			return modelo;
		}

		public void setModelo(String modelo) {
			this.modelo = modelo;
		}

		public String getMarca() {
			return marca;
		}

		public void setMarca(String marca) {
			this.marca = marca;
		}

		public String getAno() {
			return ano;
		}

		public void setAno(String ano) {
			this.ano = ano;
		}

		public Cliente getCliente() {
			return cliente;
		}

		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
		}


}

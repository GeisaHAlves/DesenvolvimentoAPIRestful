package org.serratec.api.EcommerceApi.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1334009635566596795L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cliente_cd_id")
	private Integer IdCliente;

	@Column(name = "cliente_tx_nome_completo")
	private String nomeCompleto;

	@Column(name = "cliente_tx_nome_usuario")
	private String nomeUsuario;

	@Column(name = "cliente_tx_email")
	private String email;

	@Column(name = "cliente_tx_cpf", unique = true)
	private String cpf;

	@Column(name = "cliente_dt_data_nasc")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataNasc;

	@ManyToOne
	@JoinColumn(name = "endereco_id", referencedColumnName = "endereco_cd_id")
	@JsonIgnore
	private Endereco endereco;

	@Column(name = "cliente_tx_telefone")
	private String telefone;

	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> listaPedido;

	public Cliente() {
	}

	public Integer getIdCliente() {
		return IdCliente;
	}

	public void setIdCliente(Integer idCliente) {
		IdCliente = idCliente;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<Pedido> getListaPedido() {
		return listaPedido;
	}

	public void setListaProduto(List<Pedido> listaPedido) {
		this.listaPedido = listaPedido;
	}

}
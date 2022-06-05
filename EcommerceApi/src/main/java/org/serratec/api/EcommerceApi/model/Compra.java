package org.serratec.api.EcommerceApi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "compra")
public class Compra implements Serializable {

	private static final long serialVersionUID = 2112929183198023576L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "compra_cd_id")
	private Integer IdCompra;
	
	@ManyToOne
	@JoinColumn(name="funcionario_id", referencedColumnName = "funcionario_cd_id", nullable=false)
	@JsonIgnore
	private Funcionario funcionario;
	
	@JsonIgnore
	@OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
	private List<ComprasItem> listaCompra;

	@Column(name = "compra_db_valor_total")
	private Double valorTotal;

	public Compra() {
	}
	
	public Integer getIdCompra() {
		return IdCompra;
	}

	public void setIdCompra(Integer idCompra) {
		IdCompra = idCompra;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<ComprasItem> getListaCompra() {
		return listaCompra;
	}

	public void setListaCompra(List<ComprasItem> listaCompra) {
		this.listaCompra = listaCompra;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
}

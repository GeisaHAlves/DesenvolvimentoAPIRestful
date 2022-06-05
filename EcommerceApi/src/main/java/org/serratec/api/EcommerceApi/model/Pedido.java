package org.serratec.api.EcommerceApi.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
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
@Table(name="pedido")
public class Pedido implements Serializable{

		
	private static final long serialVersionUID = 3079328192000458464L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pedido_cd_id")
	private Integer idPedido;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<VendasItem> itens = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name="cliente_id", referencedColumnName = "cliente_cd_id", nullable=false)
	private Cliente cliente;
	
	@Column(name = "pedido_nu_nota_fiscal")
	private Integer notaFiscal;
	
	@Column(name = "pedido_tx_tipo_pedido")
	private String tipoPedido;
	
	@Column(name = "pedido_db_valor_total")
	private Double valorTotal;
	
	@Column(name = "pedido_dt_data_pedido")
	private LocalDate dataPedido;
	
	public Pedido() {}
	
	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(Integer notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public String getTipoPedido() {
		return tipoPedido;
	}

	public void setTipoPedido(String tipoPedido) {
		this.tipoPedido = tipoPedido;
	}

	public List<VendasItem> getItens() {
		return itens;
	}

	public void setItens(List<VendasItem> itens) {
		this.itens = itens;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}
	
}
package org.serratec.api.EcommerceApi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="vendas_item")
public class VendasItem implements Serializable {
	
	private static final long serialVersionUID = 3405278086122615800L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="vendas_item_cd_id")
	private Integer idVendasItem;

	@ManyToOne
	@JoinColumn(name="produto_id", referencedColumnName = "produto_cd_id", nullable=false)
	private Produto produto;

	@ManyToOne
	@JoinColumn(name="pedido_id", referencedColumnName = "pedido_cd_id", nullable=false)
	private Pedido pedido;
	
	@Column(name = "vendas_item_db_preco")
	private Double preco;
	
	@Column(name = "vendas_item_nu_quantidade")
	private Integer quantidade;

	public VendasItem() {}

	public Integer getIdVendasItem() {
		return idVendasItem;
	}

	public void setIdVendasItem(Integer idVendasItem) {
		this.idVendasItem = idVendasItem;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
}

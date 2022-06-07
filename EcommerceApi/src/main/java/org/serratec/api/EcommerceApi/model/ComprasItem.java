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
@Table(name="compras_item")
public class ComprasItem implements Serializable {
	
	private static final long serialVersionUID = 5300574490830742742L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="compras_item_cd_id")
	private Integer idComprasItem;

	@ManyToOne
	@JoinColumn(name="produto_id", referencedColumnName = "produto_cd_id", nullable=false)
	private Produto produto;

	@ManyToOne
	@JoinColumn(name="compra_id", referencedColumnName = "compra_cd_id", nullable=false)
	private Compra compra;
	
	@Column(name = "compras_item_db_preco")
	private Double preco;
	
	@Column(name = "compras_item_nu_quantidade")
	private Integer quantidade;

	public ComprasItem() {}

	public Integer getIdComprasItem() {
		return idComprasItem;
	}

	public void setIdComprasItem(Integer idComprasItem) {
		this.idComprasItem = idComprasItem;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
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

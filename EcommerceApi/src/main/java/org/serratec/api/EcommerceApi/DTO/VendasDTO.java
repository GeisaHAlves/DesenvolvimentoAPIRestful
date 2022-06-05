package org.serratec.api.EcommerceApi.DTO;

import java.io.Serializable;
import java.util.List;

public class VendasDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer idPedido;
	private Integer notaFiscal;
	private Integer idCliente;
	private String tipoPedido;
	private List<ItemDTO> vendasItens;
	
	public VendasDTO() {
		super();
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Integer getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(Integer notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public List<ItemDTO> getVendasItens() {
		return vendasItens;
	}

	public void setVendasItens(List<ItemDTO> vendasItens) {
		this.vendasItens = vendasItens;
	}

	public String getTipoPedido() {
		return tipoPedido;
	}

	public void setTipoPedido(String tipoPedido) {
		this.tipoPedido = tipoPedido;
	}
	
}

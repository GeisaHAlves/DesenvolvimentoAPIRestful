package org.serratec.api.EcommerceApi.DTO;

import java.io.Serializable;
import java.util.List;

public class PedidoDTO implements Serializable {
	
		private static final long serialVersionUID = 1L;

//		private Integer idPedido;
		private Integer idCliente;
//		private Integer notaFiscal;
//		private String tipoPedido;
//		private Double valorTotal;
		private List<ItemDTO> itens;

//		public Double getValorTotal() {
//			return valorTotal;
//		}
//
//		public void setValorTotal(Double valorTotal) {
//			this.valorTotal = valorTotal;
//		}

		public PedidoDTO(){}

//		public Integer getIdPedido() {
//			return idPedido;
//		}
//
//		public void setIdPedido(Integer idPedido) {
//			this.idPedido = idPedido;
//		}

		public Integer getIdCliente() {
			return idCliente;
		}

		public void setIdCliente(Integer idCliente) {
			this.idCliente = idCliente;
		}

//		public Integer getNotaFiscal() {
//			return notaFiscal;
//		}
//
//		public void setNotaFiscal(Integer notaFiscal) {
//			this.notaFiscal = notaFiscal;
//		}
//
//		public String getTipoPedido() {
//			return tipoPedido;
//		}
//
//		public void setTipoPedido(String tipoPedido) {
//			this.tipoPedido = tipoPedido;
//		}

		public List<ItemDTO> getItens() {
			return itens;
		}

		public void setItens(List<ItemDTO> itens) {
			this.itens = itens;
		}
		
}
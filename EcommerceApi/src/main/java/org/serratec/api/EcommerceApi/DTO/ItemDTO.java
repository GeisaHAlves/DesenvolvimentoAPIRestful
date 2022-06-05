package org.serratec.api.EcommerceApi.DTO;

public class ItemDTO {

	private Integer idProduto;
	private Integer quantidade;
	
	public ItemDTO() {}
	
	public Integer getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
}

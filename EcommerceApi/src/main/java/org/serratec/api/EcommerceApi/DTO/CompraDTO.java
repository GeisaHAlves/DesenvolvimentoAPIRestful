package org.serratec.api.EcommerceApi.DTO;

import java.io.Serializable;
import java.util.List;

public class CompraDTO implements Serializable {

	private static final long serialVersionUID = 3931761282481617689L;
	
	private Integer idFuncionario;
	private List<ItemDTO> itens;
	
	public CompraDTO() {
	}
	
	public Integer getIdFuncionario() {
		return idFuncionario;
	}
	
	public void setIdFuncionario(Integer idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	
	public List<ItemDTO> getItens() {
		return itens;
	}
	
	public void setItens(List<ItemDTO> itens) {
		this.itens = itens;
	}

}

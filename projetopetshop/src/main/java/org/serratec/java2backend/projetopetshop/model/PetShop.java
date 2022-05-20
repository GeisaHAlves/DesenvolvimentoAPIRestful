package org.serratec.java2backend.projetopetshop.model;

public class PetShop {

	private Integer id;
	private String raca;
	private String descricao;

	public PetShop() {
	}

	public PetShop(Integer id, String raca, String descricao) {
		super();
		this.id = id;
		this.raca = raca;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}

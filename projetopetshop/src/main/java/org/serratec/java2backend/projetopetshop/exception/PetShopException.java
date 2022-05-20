package org.serratec.java2backend.projetopetshop.exception;

public class PetShopException extends Exception {

	private static final long serialVersionUID = 1L;

	private Integer id;

	public PetShopException(Integer id) {
		this.id = id;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}

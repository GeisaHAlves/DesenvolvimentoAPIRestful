package org.serratec.java2backend.projetopetshop.service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.java2backend.projetopetshop.exception.PetShopException;
import org.serratec.java2backend.projetopetshop.model.PetShop;
import org.springframework.stereotype.Service;

@Service
public class PetShopService {

//	private List<PetShop> petShop = Arrays.asList(
//			new PetShop(1, "Poodle", "O Poodle é a segunda raça canina mais inteligente do mundo"),
//			new PetShop(2, "Buldog", "Excelente cão de companhia, o Bulldog Inglês tem porte médio"),
//			new PetShop(3, "Pastor Belga",
//					"O cãozinho dessa raça tem porte mediano e é famoso por sua altivez e aparência elegante"),
//			new PetShop(4, "Yorkshire",
//					"também chamada york e yorkie, é uma raça canina de pequeno porte do grupo dos terriers"),
//			new PetShop(5, "Husky", "A paleta de cores dos pelos é a mais variada: vai do preto ao branco puro"));

	List<PetShop> listaPetShop = new ArrayList<>(); 

	public void adicionar(PetShop listaPet) {
		listaPetShop.add(listaPet);
	}

	public List<PetShop> listaAllPetShop() {
		return this.listaPetShop;
	}
	
	
	public void atualizar(Integer posicaoLista, PetShop conteudoDaApi) {
		PetShop petshop = listaPetShop.get(posicaoLista);

		petshop.setId(conteudoDaApi.getId());
		petshop.setDescricao(conteudoDaApi.getDescricao());
		petshop.setRaca(conteudoDaApi.getRaca());
	}

	public PetShop buscarPorId(Integer idPetShop) throws PetShopException {
		PetShop conteudoNoBanco = new PetShop();
		for (PetShop petShop : listaPetShop) {
			if (petShop.getId().equals(idPetShop)) {
				conteudoNoBanco = petShop;
			}
		}

		if (conteudoNoBanco.equals(null)) {
			throw new PetShopException(idPetShop);
		}

		return conteudoNoBanco;
	}

	public void deletar(int posicaoLista) {
		listaPetShop.remove(posicaoLista);
	}	
	
}

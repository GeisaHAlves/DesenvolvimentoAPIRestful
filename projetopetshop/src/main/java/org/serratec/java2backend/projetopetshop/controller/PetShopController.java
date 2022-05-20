package org.serratec.java2backend.projetopetshop.controller;

import java.util.List;

import org.serratec.java2backend.projetopetshop.exception.PetShopException;
import org.serratec.java2backend.projetopetshop.model.PetShop;
import org.serratec.java2backend.projetopetshop.service.PetShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conteudo")
public class PetShopController {

	@Autowired
	PetShopService petShopService;
	
	@GetMapping("/lista")
	public ResponseEntity<List<PetShop>> getPetShop() {
		return new ResponseEntity<>(petShopService.listaAllPetShop(), HttpStatus.OK);
	}
	
	@GetMapping("/buscar/{posicaoLista}")
	public ResponseEntity<PetShop> getPetShop(@PathVariable Integer posicaoLista) throws PetShopException {
		return new ResponseEntity<>(petShopService.buscarPorId(posicaoLista), HttpStatus.OK);
	}
	
	@PostMapping("/adicionar")
	public ResponseEntity<String> addPetShop(@RequestBody PetShop petShop) {
		petShopService.adicionar(petShop);
		return new ResponseEntity<>("Adicionado com sucesso", HttpStatus.CREATED);
	}
	
	@PutMapping("/alterar/{posicaoLista}")
	public ResponseEntity<String> updatePetShop(@PathVariable Integer posicaoLista, @RequestBody PetShop conteudo) {
		petShopService.atualizar(posicaoLista, conteudo);
		return new ResponseEntity<>("Alterado com sucesso", HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deletar/{posicaoLista}")
	public ResponseEntity<String> deletePetShop(@PathVariable Integer posicaoLista) {
		petShopService.deletar(posicaoLista);
		return new ResponseEntity<>("Deletado com sucesso", HttpStatus.ACCEPTED);
	}
	
	
}

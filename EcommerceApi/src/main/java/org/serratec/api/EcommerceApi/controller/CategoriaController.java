package org.serratec.api.EcommerceApi.controller;

import java.util.List;

import org.serratec.api.EcommerceApi.DTO.CategoriaDTO;
import org.serratec.api.EcommerceApi.exception.CategoriaException;
import org.serratec.api.EcommerceApi.service.CategoriaService;
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
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;

	@PostMapping("/salvar")
	public ResponseEntity<Void> salvar(@RequestBody CategoriaDTO categoriaDTO) throws CategoriaException {
		categoriaService.salvar(categoriaDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/buscar/{idCategoria}")
	public ResponseEntity<CategoriaDTO> buscarPorId(@PathVariable Integer idCategoria) {
		try {
			return ResponseEntity.ok(categoriaService.buscarPorId(idCategoria));
		} catch (CategoriaException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/atualizar/{idCategoria}")
	public ResponseEntity<String> atualizar(@PathVariable Integer idCategoria, @RequestBody CategoriaDTO categoriaDTO)
			throws CategoriaException {
		return new ResponseEntity<>(categoriaService.atualizar(idCategoria, categoriaDTO), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{idCategoria}")
	public ResponseEntity<String> delete(@PathVariable Integer idCategoria) throws CategoriaException {
		return new ResponseEntity<>(categoriaService.delete(idCategoria), HttpStatus.NO_CONTENT);
	}

	@GetMapping("/lista")
	public ResponseEntity<List<CategoriaDTO>> listaTodos() {
		return ResponseEntity.ok(categoriaService.todosCategorias());
	}

	@PostMapping("/salvar-lista")
	public ResponseEntity<Void> salvarLista(@RequestBody List<CategoriaDTO> listaCategoriaDTO) {
		categoriaService.salvarListaCategoria(listaCategoriaDTO);
		return ResponseEntity.status(HttpStatus.CREATED).build();

	}
}

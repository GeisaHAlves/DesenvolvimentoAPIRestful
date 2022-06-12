package org.serratec.api.EcommerceApi.controller;

import java.util.List;

import org.serratec.api.EcommerceApi.DTO.ProdutoDTO;
import org.serratec.api.EcommerceApi.DTO.RelatorioDTO;
import org.serratec.api.EcommerceApi.exception.ProdutoException;
import org.serratec.api.EcommerceApi.service.ProdutoService;
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
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	ProdutoService produtoService;
	
	@PostMapping("/salvar")
	public ResponseEntity<Void> salvar(@RequestBody ProdutoDTO produtoDTO) throws ProdutoException{
		produtoService.salvar(produtoDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/buscar/{idProduto}")
	public ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable Integer idProduto) {
		try {
			return ResponseEntity.ok(produtoService.buscarPorId(idProduto));
		} catch (ProdutoException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/atualizar/{idProduto}") 
    public ResponseEntity<String> atualizar(@PathVariable Integer idProduto,@RequestBody ProdutoDTO produtoDTO) throws ProdutoException{
        return new ResponseEntity<>( produtoService.atualizar(idProduto, produtoDTO),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{idProduto}")
	public ResponseEntity<String> delete(@PathVariable Integer idProduto) throws ProdutoException {
				return new ResponseEntity<>(produtoService.delete(idProduto),HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/lista")
	public ResponseEntity<List<ProdutoDTO>> listaTodos(){
		return ResponseEntity.ok(produtoService.todosProdutos());
	}
	

	@PostMapping("/salvar-lista")
    public ResponseEntity<Void> salvarLista(@RequestBody List<ProdutoDTO> listaProdutoDTO){
        produtoService.salvarListaProduto(listaProdutoDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
	@GetMapping("/relatorio")
	public List<RelatorioDTO> relatorio() {
		return produtoService.relatorio();
	}
}

package org.serratec.api.EcommerceApi.controller;

import java.util.List;

import org.serratec.api.EcommerceApi.DTO.ProdutoDTO;
import org.serratec.api.EcommerceApi.DTO.RelatorioDTO;
import org.serratec.api.EcommerceApi.exception.ProdutoException;
import org.serratec.api.EcommerceApi.model.Produto;
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
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<Produto> buscarPorId(@PathVariable Integer idProduto) throws ProdutoException {
		produtoService.buscarPorId(idProduto);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/atualizar/{idProduto}") 
    public ResponseEntity<Void> atualizar(@PathVariable Integer idProduto,@RequestBody ProdutoDTO produtoDTO) throws ProdutoException{
        produtoService.atualizar(idProduto, produtoDTO);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{idProduto}")
	public ResponseEntity<Void> delete(@PathVariable Integer idProduto) {
		produtoService.delete(idProduto);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/lista")
	public ResponseEntity<List<Produto>> listaTodos(){
		produtoService.todosProdutos();
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/estoque")
	public ResponseEntity<Void>addEstoque(@RequestParam Integer idProduto, @RequestParam Integer qtd) throws ProdutoException{
		produtoService.addEstoque(idProduto, qtd);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
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

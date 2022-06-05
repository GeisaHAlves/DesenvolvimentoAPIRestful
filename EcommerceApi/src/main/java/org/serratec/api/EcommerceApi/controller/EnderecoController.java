package org.serratec.api.EcommerceApi.controller;

import java.util.List;

import org.serratec.api.EcommerceApi.DTO.EnderecoDTO;
import org.serratec.api.EcommerceApi.exception.EnderecoException;
import org.serratec.api.EcommerceApi.model.Endereco;
import org.serratec.api.EcommerceApi.service.EnderecoService;
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
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    EnderecoService enderecoService;

    @PostMapping("/salvar")
	public ResponseEntity<Void> salvar(@RequestBody EnderecoDTO enderecoDTO) throws EnderecoException{
		enderecoService.salvar(enderecoDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/buscar/{idEndereco}")
	public ResponseEntity<Endereco> buscarPorId(@PathVariable Long idEndereco) throws EnderecoException {
		enderecoService.buscarPorId(idEndereco);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/atualizar/{idEndereco}") 
    public ResponseEntity<Void> atualizar(@PathVariable Long idEndereco,@RequestBody EnderecoDTO enderecoDTO) throws EnderecoException{
        enderecoService.atualizar(idEndereco, enderecoDTO);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{idEndereco}")
	public ResponseEntity<Void> delete(@PathVariable Long idEndereco) {
		enderecoService.delete(idEndereco);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/lista")
	public ResponseEntity<List<Endereco>> listaTodos(){
		enderecoService.todosEnderecos();
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}


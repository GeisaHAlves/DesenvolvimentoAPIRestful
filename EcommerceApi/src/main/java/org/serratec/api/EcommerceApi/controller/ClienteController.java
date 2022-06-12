package org.serratec.api.EcommerceApi.controller;

import java.util.List;

import org.serratec.api.EcommerceApi.DTO.ClienteDTO;
import org.serratec.api.EcommerceApi.exception.ClienteException;
import org.serratec.api.EcommerceApi.service.ClienteService;
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
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@PostMapping("/salvar")
	public ResponseEntity<Void> salvar(@RequestBody ClienteDTO clienteDTO) throws ClienteException {
		clienteService.salvar(clienteDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/buscar/{idCliente}")
	public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Integer idCliente) {
		try {
			return ResponseEntity.ok(clienteService.buscarPorId(idCliente));
		} catch (ClienteException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} 
			
	}

	@PutMapping("/atualizar/{idCliente}")
	public ResponseEntity<String> atualizar(@PathVariable Integer idCliente, @RequestBody ClienteDTO clienteDTO)
			throws ClienteException {
		return new ResponseEntity<>(clienteService.atualizar(idCliente, clienteDTO), 
				HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{idCliente}")
	public ResponseEntity<String> delete(@PathVariable Integer idCliente) throws ClienteException{
		return new ResponseEntity<>(clienteService.delete(idCliente),
				HttpStatus.NO_CONTENT);
	}

	@GetMapping("/lista")
	public ResponseEntity<List<ClienteDTO>> listaTodos() {
		return ResponseEntity.ok(clienteService.todosClientes()); 
				
	}

	@PostMapping("/salvar-lista")
	public ResponseEntity<Void> salvarLista(@RequestBody List<ClienteDTO> listaClienteDTO) {
		clienteService.salvarListaCliente(listaClienteDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}

package org.serratec.api.EcommerceApi.controller;

import java.util.List;

import javax.mail.MessagingException;

import org.serratec.api.EcommerceApi.DTO.CompraDTO;
import org.serratec.api.EcommerceApi.DTO.PedidoDTO;
import org.serratec.api.EcommerceApi.exception.EmailException;
import org.serratec.api.EcommerceApi.exception.EstoqueException;
import org.serratec.api.EcommerceApi.exception.PedidoException;
import org.serratec.api.EcommerceApi.model.Pedido;
import org.serratec.api.EcommerceApi.service.CompraService;
import org.serratec.api.EcommerceApi.service.PedidoService;
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
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	PedidoService pedidoService;
	
	@Autowired
	CompraService compraService;

	@PostMapping("/salvar")
	public ResponseEntity<Void> salvar(@RequestBody PedidoDTO pedidoDTO) 
			throws PedidoException, EmailException, MessagingException, EstoqueException{
		pedidoService.salvar(pedidoDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PostMapping("/salvarCompra")
	public ResponseEntity<Void> salvarCompra(@RequestBody CompraDTO compraDTO) {
		compraService.salvar(compraDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/buscar/{idPedido}")
	public ResponseEntity<Pedido> buscarPorId(@PathVariable Integer idPedido) throws PedidoException {
		pedidoService.buscarPorId(idPedido);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/atualizar/{idPedido}") 
    public ResponseEntity<Void> atualizar(@PathVariable Integer idPedido,@RequestBody PedidoDTO pedidoDTO) throws PedidoException{
        pedidoService.atualizar(idPedido, pedidoDTO);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{idPedido}")
	public ResponseEntity<Void> delete(@PathVariable Integer idPedido) throws PedidoException {
		pedidoService.delete(idPedido);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/lista")
	public ResponseEntity<List<Pedido>> listaTodos(){
		pedidoService.todosPedidos();
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}

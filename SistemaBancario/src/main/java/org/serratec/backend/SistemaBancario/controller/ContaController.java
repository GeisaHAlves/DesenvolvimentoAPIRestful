package org.serratec.backend.SistemaBancario.controller;

import java.util.List;

import org.serratec.backend.SistemaBancario.exception.OperacaoInvalidaException;
import org.serratec.backend.SistemaBancario.exception.SaldoInsuficienteException;
import org.serratec.backend.SistemaBancario.exception.ValorInvalidoException;
import org.serratec.backend.SistemaBancario.model.Conta;
import org.serratec.backend.SistemaBancario.service.ContaService;
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
@RequestMapping("/conta")
public class ContaController {
	
	@Autowired
	ContaService service;
	
	@GetMapping("/")
	public ResponseEntity<List<Conta>> listarTodos() {
		return ResponseEntity.ok(service.listarTodas());
	}
	
	@GetMapping("/{numero}")
	public ResponseEntity<Conta> buscarPorNumero(@PathVariable String numero) {
		return ResponseEntity.ok(service.buscarPorNumero(numero));
	}
	
	@PostMapping("/")
	public ResponseEntity<String> adicionarConta(@RequestBody Conta conta) {
		service.adicionar(conta);
		return new ResponseEntity<>("Conta criada com sucesso", HttpStatus.CREATED);
	}

	@PutMapping("/{numero}")
	public ResponseEntity<Conta> atualizar(@PathVariable String numero, @RequestBody Conta conta) {
		return new ResponseEntity<>(service.atualizar(numero, conta), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{numero}")
	public ResponseEntity<Void> delete(@PathVariable String numero) {
		service.delete(numero);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	

	@PostMapping("/numero/{operacao}")
	public ResponseEntity<String> operacoes(@PathVariable String operacao, @RequestParam String numero, @RequestParam Double valor) {
		try {
			service.operacao(operacao, numero, valor);
		} catch (ValorInvalidoException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (SaldoInsuficienteException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (OperacaoInvalidaException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Operação efetuada com sucesso!", HttpStatus.ACCEPTED);
	}

}

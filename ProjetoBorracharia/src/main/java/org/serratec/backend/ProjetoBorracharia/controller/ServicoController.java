package org.serratec.backend.ProjetoBorracharia.controller;

import java.util.List;

import javax.mail.MessagingException;

import org.serratec.backend.ProjetoBorracharia.DTO.RelatorioDTO;
import org.serratec.backend.ProjetoBorracharia.DTO.ServicoDTO;
import org.serratec.backend.ProjetoBorracharia.exception.EmailException;
import org.serratec.backend.ProjetoBorracharia.exception.ServicoException;
import org.serratec.backend.ProjetoBorracharia.service.ServicoService;
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
@RequestMapping("/servico")
public class ServicoController {

	@Autowired
	ServicoService servicoService;

	@PostMapping("/salvar")
	public ResponseEntity<String> salvar(@RequestBody ServicoDTO servicoDTO) throws EmailException, MessagingException {
		return ResponseEntity.ok(servicoService.salvar(servicoDTO));
	}

	@GetMapping("/buscar/{idServico}")
	public ResponseEntity<ServicoDTO> buscarPorId(@PathVariable Integer idServico) throws ServicoException {
		return ResponseEntity.ok(servicoService.buscarPorId(idServico));
	}

	@PutMapping("/atualizar/{idServico}")
	public ResponseEntity<String> atualizar(@PathVariable Integer idServico, @RequestBody ServicoDTO servicoDTO)
			throws ServicoException {
		return ResponseEntity.ok(servicoService.atualizar(idServico, servicoDTO));
	}

	@DeleteMapping("delete/{idServico}")
	public ResponseEntity<Void> deletar(@PathVariable Integer idServico) throws ServicoException {
		servicoService.deletar(idServico);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@GetMapping("/lista")
	public ResponseEntity<List<ServicoDTO>> listarTodos() {
		return ResponseEntity.ok(servicoService.buscarTodos());
	}

	@PostMapping("/salvar-lista")
	public ResponseEntity<Void> salvarLista(@RequestBody List<ServicoDTO> listaServicoDTO) {
		servicoService.salvarListaServico(listaServicoDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/relatorio")
	public List<RelatorioDTO> relatorio() {
		return servicoService.relatorio();
	}

}

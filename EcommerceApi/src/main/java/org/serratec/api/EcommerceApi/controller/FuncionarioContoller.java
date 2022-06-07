package org.serratec.api.EcommerceApi.controller;

import java.util.List;

import org.serratec.api.EcommerceApi.DTO.FuncionarioDTO;
import org.serratec.api.EcommerceApi.exception.FuncionarioException;
import org.serratec.api.EcommerceApi.model.Funcionario;
import org.serratec.api.EcommerceApi.service.FuncionarioService;
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
@RequestMapping("/funcionario")
public class FuncionarioContoller {

	@Autowired
	FuncionarioService funcionarioService;
	
	@PostMapping("/salvar")
	public ResponseEntity<Void> salvar(@RequestBody FuncionarioDTO funcionarioDTO) throws FuncionarioException{
		funcionarioService.salvar(funcionarioDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/buscar/{idFuncionario}")
	public ResponseEntity<Funcionario> buscarPorId(@PathVariable Integer idFuncionario) throws FuncionarioException {
		funcionarioService.buscarPorId(idFuncionario);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/atualizar/{idFuncionario}") 
    public ResponseEntity<Void> atualizar(@PathVariable Integer idFuncionario,@RequestBody FuncionarioDTO funcionarioDTO) throws FuncionarioException{
        funcionarioService.atualizar(idFuncionario, funcionarioDTO);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{idFuncionario}")
	public ResponseEntity<String> delete(@PathVariable Integer idFuncionario) throws FuncionarioException{
		return new ResponseEntity<>(funcionarioService.delete(idFuncionario),
				HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/lista")
	public ResponseEntity<List<FuncionarioDTO>> listaTodos(){
				return new ResponseEntity<>(funcionarioService.todosFuncionarios(),
						HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/salvar-lista")
    public ResponseEntity<Void> salvarLista(@RequestBody List<FuncionarioDTO> listaFuncionarioDTO){
        funcionarioService.salvarListaFuncionario(listaFuncionarioDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

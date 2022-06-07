package org.serratec.api.EcommerceApi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.api.EcommerceApi.DTO.FuncionarioDTO;
import org.serratec.api.EcommerceApi.exception.FuncionarioException;
import org.serratec.api.EcommerceApi.model.Funcionario;
import org.serratec.api.EcommerceApi.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	public Funcionario toModel(Funcionario funcionario, FuncionarioDTO funcionarioDTO) {
		funcionario.setCpf(funcionarioDTO.getCpf());
		funcionario.setNome(funcionarioDTO.getNome());
		return funcionario;
	}
	
	public FuncionarioDTO toDTO(FuncionarioDTO funcionarioDTO, Funcionario funcionario) {
		funcionarioDTO.setIdFuncionario(funcionario.getIdFuncionario());
		funcionarioDTO.setCpf(funcionario.getCpf());
		funcionarioDTO.setNome(funcionario.getNome());
		return funcionarioDTO;
	}
	
	public String salvar(FuncionarioDTO funcionarioDTO) {
		Funcionario funcionario = new Funcionario();
		funcionario = toModel(funcionario, funcionarioDTO);
		funcionarioRepository.save(funcionario);
		return "Novo funcionario cadastrado.\nNome: " + funcionario.getNome()
		+ "\nID funcionario: " + funcionario.getIdFuncionario();
	}
	
	public FuncionarioDTO buscarPorId(Integer idFuncionario) throws FuncionarioException {
		Optional<Funcionario> funOptional = funcionarioRepository.findById(idFuncionario);
		Funcionario funcionario = new Funcionario();
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		
		if(funOptional.isPresent()) {
			funcionario = funOptional.get();
			funcionarioDTO = toDTO(funcionarioDTO, funcionario);
			return funcionarioDTO;
		}
		throw new FuncionarioException("Funcionario não econtrado.");
	}
	
	public String delete(Integer idFuncionario) throws FuncionarioException {
Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(idFuncionario);
		
		if (funcionarioOptional.isPresent()) {
		funcionarioRepository.deleteById(idFuncionario);
		return "Funcionario deletado com sucesso!";
	}
		throw new FuncionarioException("Cliente não encontrado");
	}

	
	public String atualizar(Integer idFuncionario, FuncionarioDTO funcionarioDTO) throws FuncionarioException {
		Optional<Funcionario> funOptional = funcionarioRepository.findById(idFuncionario);
		Funcionario funcionario = new Funcionario();
		
		if(funOptional.isPresent()) {
			funcionario = funOptional.get();
			if(funcionarioDTO.getCpf()!= null) {
				funcionario.setCpf(funcionarioDTO.getCpf());
			}
			if(funcionarioDTO.getNome()!= null) {
				funcionario.setNome(funcionarioDTO.getNome());
			}
			funcionarioRepository.save(funcionario);
			return "Funcionario " + funcionario.getNome() + " foi atualizado.";
		}
		throw new FuncionarioException("O funcionario não foi atualizado");
	}
	
	public List<FuncionarioDTO> todosFuncionarios(){
		List<Funcionario> lisFuncionarios = funcionarioRepository.findAll();
		List<FuncionarioDTO> funcionarioDTOs = new ArrayList<>();
		
		for (Funcionario funcionario : lisFuncionarios) {
			FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
			funcionarioDTO = toDTO(funcionarioDTO, funcionario);
			funcionarioDTOs.add(funcionarioDTO);
		}
		return funcionarioDTOs;
	}
	public void salvarListaFuncionario(List<FuncionarioDTO> listaFuncionarioDTO) {
		for (FuncionarioDTO funcionarioDTO : listaFuncionarioDTO) {
			Funcionario funcionario = new Funcionario();
			funcionario = toModel(funcionario, funcionarioDTO);
			funcionarioRepository.save(funcionario);
		}
	}
}

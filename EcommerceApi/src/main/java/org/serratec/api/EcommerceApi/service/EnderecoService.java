package org.serratec.api.EcommerceApi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.api.EcommerceApi.DTO.EnderecoDTO;
import org.serratec.api.EcommerceApi.exception.EnderecoException;
import org.serratec.api.EcommerceApi.model.Endereco;
import org.serratec.api.EcommerceApi.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	public Endereco toModel(Endereco endereco, EnderecoDTO enderecoDTO) {
		endereco.setCep(enderecoDTO.getCep());
		endereco.setComplemento(enderecoDTO.getComplemento());
		endereco.setNumero(enderecoDTO.getNumero());
		return endereco;
	}

	public EnderecoDTO toDTO(EnderecoDTO enderecoDTO, Endereco endereco) {
		enderecoDTO.setCep(endereco.getCep());
		enderecoDTO.setComplemento(endereco.getComplemento());
		enderecoDTO.setNumero(endereco.getNumero());
		return enderecoDTO;
	}

	public String salvar(EnderecoDTO enderecoDTO) {
		Endereco endereco = new Endereco();
		toModel(endereco, enderecoDTO);
		enderecoRepository.save(endereco);
		return "Novo endereco cadastrado.";
	}

	public EnderecoDTO buscarPorId(Long idEndereco) throws EnderecoException {
		Optional<Endereco> funOptional = enderecoRepository.findById(idEndereco);
		Endereco endereco = new Endereco();
		EnderecoDTO enderecoDTO = new EnderecoDTO();

		if (funOptional.isPresent()) {
			endereco = funOptional.get();
			toDTO(enderecoDTO, endereco);
			return enderecoDTO;
		}
		throw new EnderecoException("Endereco não encontrada.");
	}

	public void delete(Long idEndereco) {
		enderecoRepository.deleteById(idEndereco);
	}

	public String atualizar(Long idEndereco, EnderecoDTO enderecoDTO) throws EnderecoException {
		Optional<Endereco> funOptional = enderecoRepository.findById(idEndereco);
		Endereco endereco = new Endereco();

		if (funOptional.isPresent()) {
			endereco = funOptional.get();
			if (enderecoDTO.getCep() != null) {
				endereco.setCep(endereco.getCep());
			}
			if (enderecoDTO.getComplemento() != null) {
				endereco.setComplemento(endereco.getComplemento());
			}
			if (enderecoDTO.getNumero() != null) {
				endereco.setNumero(endereco.getNumero());
			}
			enderecoRepository.save(endereco);
			return "Endereco atualizado.";
		}
		throw new EnderecoException("O endereco não foi atualizado");
	}

	public List<EnderecoDTO> todosEnderecos() {
		List<Endereco> lisEnderecos = enderecoRepository.findAll();
		List<EnderecoDTO> enderecoDTOs = new ArrayList<EnderecoDTO>();

		for (Endereco endereco : lisEnderecos) {
			EnderecoDTO enderecoDTO = new EnderecoDTO();
			toDTO(enderecoDTO, endereco);
			enderecoRepository.save(endereco);
		}
		return enderecoDTOs;
	}

}

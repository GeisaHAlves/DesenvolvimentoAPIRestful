package org.serratec.api.EcommerceApi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.api.EcommerceApi.DTO.ClienteDTO;
import org.serratec.api.EcommerceApi.DTO.EnderecoDTO;
import org.serratec.api.EcommerceApi.exception.ClienteException;
import org.serratec.api.EcommerceApi.model.Cliente;
import org.serratec.api.EcommerceApi.model.Endereco;
import org.serratec.api.EcommerceApi.repository.ClienteRepository;
import org.serratec.api.EcommerceApi.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	EnderecoRepository enderecoRepository;

	public Cliente toModel(Cliente cliente, ClienteDTO clienteDTO) {
		cliente.setCpf(clienteDTO.getCpf());
		cliente.setNomeCompleto(clienteDTO.getNomeCompleto());
		cliente.setDataNasc(clienteDTO.getDataNasc());
		cliente.setEmail(clienteDTO.getEmail());
		cliente.setEndereco(clienteDTO.getEnderecoDTO().toEndereco());
		cliente.setNomeUsuario(clienteDTO.getNomeUsuario());
		cliente.setTelefone(clienteDTO.getTelefone());

		return cliente;
	}

	public ClienteDTO toDTO(ClienteDTO clienteDTO, Cliente cliente) {
		clienteDTO.setIdCliente(cliente.getIdCliente());
		clienteDTO.setCpf(cliente.getCpf());
		clienteDTO.setNomeCompleto(cliente.getNomeCompleto());
		clienteDTO.setDataNasc(cliente.getDataNasc());
		clienteDTO.setEmail(cliente.getEmail());
		clienteDTO.setEnderecoDTO(toEnderecoDTO(cliente.getEndereco()));
		clienteDTO.setNomeUsuario(cliente.getNomeUsuario());
		clienteDTO.setTelefone(cliente.getTelefone());
		return clienteDTO;
	}
	
	public EnderecoDTO toEnderecoDTO(Endereco endereco) {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setCep(endereco.getCep());
        enderecoDTO.setComplemento(endereco.getComplemento());
        enderecoDTO.setNumero(endereco.getNumero());
        return enderecoDTO;
    }

	public String salvar(ClienteDTO clienteDTO) {
		Cliente cliente = new Cliente();
		cliente = toModel(cliente, clienteDTO);
		enderecoRepository.save(cliente.getEndereco());
		clienteRepository.save(cliente);
		return "Cliente " + cliente.getNomeCompleto() + " cadastrado." + "\nID cliente: " + cliente.getIdCliente();
	}

	public ClienteDTO buscarPorId(Integer idCliente) throws ClienteException {
		Optional<Cliente> funOptional = clienteRepository.findById(idCliente);
		Cliente cliente = new Cliente();
		ClienteDTO clienteDTO = new ClienteDTO();

		if (funOptional.isPresent()) {
			cliente = funOptional.get();
			clienteDTO = toDTO(clienteDTO, cliente);
			return clienteDTO;
		}
		throw new ClienteException("Cliente não encontrado");
	}

	public String delete(Integer idCliente) throws ClienteException{
		Optional<Cliente> clienteOptional = clienteRepository.findById(idCliente);
		
		if (clienteOptional.isPresent()) {
		clienteRepository.deleteById(idCliente);
		return "Cliente deletado com sucesso!";
	}
		throw new ClienteException("Cliente não encontrado");
	}

	
	public String atualizar(Integer idCliente, ClienteDTO clienteDTO) throws ClienteException {
		Optional<Cliente> clienteOptional = clienteRepository.findById(idCliente);
		Cliente cliente = new Cliente();

		if (clienteOptional.isPresent()) {
			cliente = clienteOptional.get();
			if (clienteDTO.getCpf() != null) {
				cliente.setCpf(clienteDTO.getCpf());
			}
			if (clienteDTO.getNomeCompleto() != null) {
				cliente.setNomeCompleto(clienteDTO.getNomeCompleto());
			}
			if (clienteDTO.getDataNasc() != null) {
				cliente.setDataNasc(clienteDTO.getDataNasc());
			}
			if (clienteDTO.getEmail() != null) {
				cliente.setEmail(clienteDTO.getEmail());
			}
			if (clienteDTO.getEnderecoDTO() != null) {
				cliente.setEndereco(clienteDTO.getEnderecoDTO().toEndereco());
			}
			if (clienteDTO.getNomeUsuario() != null) {
				cliente.setNomeUsuario(clienteDTO.getNomeUsuario());
			}
			if (clienteDTO.getTelefone() != null) {
				cliente.setTelefone(clienteDTO.getTelefone());
			}
			clienteRepository.save(cliente);
			return "Cliente " + cliente.getNomeCompleto() + " foi atualizado.";
		}
			throw new ClienteException("O cliente não foi atualizado");
	}

	public List<ClienteDTO> todosClientes() {
		List<Cliente> lisClientes = clienteRepository.findAll();
		List<ClienteDTO> clienteDTOs = new ArrayList<>();

		for (Cliente cliente : lisClientes) {
			ClienteDTO clienteDTO = new ClienteDTO();
			clienteDTO = toDTO(clienteDTO, cliente);
			clienteDTOs.add(clienteDTO);
		}
		return clienteDTOs;
	}

	public void salvarListaCliente(List<ClienteDTO> listaClienteDTO) {
		for (ClienteDTO clienteDTO : listaClienteDTO) {
			Cliente cliente = new Cliente();
			cliente = toModel(cliente, clienteDTO);
			clienteRepository.save(cliente);
		}
	}
}
package org.serratec.backend.ProjetoBorracharia.service;

import java.util.List;
import java.util.Optional;

import org.serratec.backend.ProjetoBorracharia.DTO.ClienteDTO;
import org.serratec.backend.ProjetoBorracharia.exception.ClienteException;
import org.serratec.backend.ProjetoBorracharia.model.Cliente;
import org.serratec.backend.ProjetoBorracharia.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	public void salvar(ClienteDTO clienteDTO) {
		Cliente cliente = new Cliente();
		transformarClienteDTOEmClienteModel(clienteDTO, cliente);
		clienteRepository.save(cliente);

	}

	public Cliente transformarClienteDTOEmClienteModel(ClienteDTO clienteDTO, Cliente cliente) {

		cliente.setCpf(clienteDTO.getCpf().replace(".", "").replace("-", ""));
		cliente.setEmail(clienteDTO.getEmail());
		cliente.setNome(clienteDTO.getNome());
		cliente.setNumeroTelefone(clienteDTO.getNumeroTelefone());

		return cliente;
	}

	public ClienteDTO transformarClienteModelEmClienteDTO(ClienteDTO clienteDTO, Cliente cliente) {
		clienteDTO.setIdCliente(cliente.getIdCliente());
		clienteDTO.setCpf(cliente.getCpf());
		clienteDTO.setEmail(cliente.getEmail());
		clienteDTO.setNome(cliente.getNome());
		clienteDTO.setNumeroTelefone(cliente.getNumeroTelefone());

		return clienteDTO;
	}

	public ClienteDTO buscarPorId(Integer idCliente) throws ClienteException {
		Optional<Cliente> cliente = clienteRepository.findById(idCliente);
		ClienteDTO clienteDTO = new ClienteDTO();
		if (cliente.isPresent()) {
			clienteDTO = transformarClienteModelEmClienteDTO(clienteDTO, cliente.get());
			return clienteDTO;
		}
		throw new ClienteException("Cliente não Encontrado");
	}

	public void atualizar(Integer idCliente, ClienteDTO clienteDTO) {
		Optional<Cliente> cliente = clienteRepository.findById(idCliente);
		Cliente clienteNoBanco = new Cliente();

		if (cliente.isPresent()) {
			clienteNoBanco = cliente.get();
			if (clienteDTO.getCpf() != null) {
				clienteNoBanco.setCpf(clienteDTO.getCpf());
			}

			if (clienteDTO.getEmail() != null) {
				clienteNoBanco.setEmail(clienteDTO.getEmail());
			}
			if (clienteDTO.getNome() != null) {
				clienteNoBanco.setNome(clienteDTO.getNome());
			}
			if (clienteDTO.getNumeroTelefone() != null) {
				clienteNoBanco.setNumeroTelefone(clienteDTO.getNumeroTelefone());
			}
			clienteRepository.save(clienteNoBanco);
		}
	}

	public void delete(Integer idCliente) {
		clienteRepository.deleteById(idCliente);
	}

	public List<Cliente> listarTodos() {
		return clienteRepository.findAll();
	}

	public void salvarTodos(List<Cliente> listaCliente) {
		clienteRepository.saveAll(listaCliente);
	}

}
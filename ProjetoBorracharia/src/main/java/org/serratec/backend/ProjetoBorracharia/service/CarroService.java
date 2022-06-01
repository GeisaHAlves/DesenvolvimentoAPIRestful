package org.serratec.backend.ProjetoBorracharia.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.serratec.backend.ProjetoBorracharia.DTO.CarroDTO;
import org.serratec.backend.ProjetoBorracharia.exception.CarroException;
import org.serratec.backend.ProjetoBorracharia.exception.EmailException;
import org.serratec.backend.ProjetoBorracharia.model.Carro;
import org.serratec.backend.ProjetoBorracharia.repository.CarroRepository;
import org.serratec.backend.ProjetoBorracharia.repository.ClienteRepository;
import org.serratec.backend.ProjetoBorracharia.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarroService {

	@Autowired
	CarroRepository carroRepository;

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	ServicoRepository servicorepository;

	@Autowired
	EmailService emailService;

	public Carro save(Carro carro) {
		return carroRepository.save(carro);
	}

	public CarroDTO transformaModelEmDTO(Carro carro, CarroDTO carroDTO) {
		carroDTO.setIdCarro(carro.getIdCarro());
		carroDTO.setModelo(carro.getModelo());
		carroDTO.setMarca(carro.getMarca());
		carroDTO.setAno(carro.getAno());

		return carroDTO;
	}

	public Carro transformaDTOEmModel(Carro carro, CarroDTO carroDTO) {
		carro.setModelo(carroDTO.getModelo());
		carro.setMarca(carroDTO.getMarca());
		carro.setAno(carroDTO.getAno());

		if (carroDTO.getIdCliente() != null) {
			carro.setCliente(clienteRepository.findById(carroDTO.getIdCliente()).get());
		}

		return carro;
	}

	public String salvar(CarroDTO carroDTO) throws EmailException, MessagingException {
		Carro carro = new Carro();
		transformaDTOEmModel(carro, carroDTO);
		carroRepository.save(carro);
		return "O carro foi adicionado com o id: " + carro.getIdCarro();
	}

	public CarroDTO buscarPorId(Integer idCarro) throws CarroException {
		Optional<Carro> carro = carroRepository.findById(idCarro);
		Carro carroDoCliente = new Carro();
		CarroDTO carroDTO = new CarroDTO();
		if (carro.isPresent()) {
			carroDoCliente = carro.get();
			transformaModelEmDTO(carroDoCliente, carroDTO);
			return carroDTO;
		}
		throw new CarroException("Carro com o id informado nao encontrado");

	}

	public void deletar(Integer idCarro) {
		carroRepository.deleteById(idCarro);
	}

	public String atualizar(Integer idCarro, CarroDTO carroDTO) throws CarroException {
		Optional<Carro> carro = carroRepository.findById(idCarro);
		Carro carroNovo = new Carro();
		if (carro.isPresent()) {
			carroNovo = carro.get();

			if (carroDTO.getModelo() != null) {
				carroNovo.setModelo(carroDTO.getModelo());
			}
			if (carroDTO.getMarca() != null) {
				carroNovo.setMarca(carroDTO.getMarca());
			}
			if (carroDTO.getAno() != null) {
				carroNovo.setAno(carroDTO.getAno());
			}
			carroRepository.save(carroNovo);
			return "O carro com o id " + carroNovo.getIdCarro() + " foi atualizado";
		}
		throw new CarroException("O carro nao foi atualizado");
	}

	public List<CarroDTO> buscarTodos() {
		List<Carro> listaCarroModel = carroRepository.findAll();
		List<CarroDTO> listaCarroDTO = new ArrayList<>();
		for (Carro carro : listaCarroModel) {
			CarroDTO carroDTO = new CarroDTO();
			transformaModelEmDTO(carro, carroDTO);
			listaCarroDTO.add(carroDTO);
		}
		return listaCarroDTO;
	}

	public void salvarListaCarro(List<CarroDTO> listaCarroDTO) {

	}
}
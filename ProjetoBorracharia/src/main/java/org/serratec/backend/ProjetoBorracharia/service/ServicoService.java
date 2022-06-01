package org.serratec.backend.ProjetoBorracharia.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.serratec.backend.ProjetoBorracharia.DTO.RelatorioDTO;
import org.serratec.backend.ProjetoBorracharia.DTO.ServicoDTO;
import org.serratec.backend.ProjetoBorracharia.exception.EmailException;
import org.serratec.backend.ProjetoBorracharia.exception.ServicoException;
import org.serratec.backend.ProjetoBorracharia.model.Servico;
import org.serratec.backend.ProjetoBorracharia.repository.CarroRepository;
import org.serratec.backend.ProjetoBorracharia.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoService {

	@Autowired
	ServicoRepository servicoRepository;

	@Autowired
	CarroRepository carroRepository;

	@Autowired
	EmailService emailService;

	public Servico save(Servico servico) {
		return servicoRepository.save(servico);
	}

	public ServicoDTO transformaModelEmDTO(Servico servico, ServicoDTO servicoDTO) {
		servicoDTO.setIdServico(servico.getIdServico());
		servicoDTO.setServicoPrestado(servico.getServicoPrestado());
		servicoDTO.setDataServico(servico.getDataServico());
		servicoDTO.setValor(servico.getValor());

		return servicoDTO;
	}

	public Servico transformaDTOEmModel(Servico servico, ServicoDTO servicoDTO) {
		servico.setServicoPrestado(servicoDTO.getServicoPrestado());
		servico.setDataServico(servicoDTO.getDataServico());
		servico.setValor(servicoDTO.getValor());

		if (servicoDTO.getIdCarro() != null) {
			servico.setCarro(carroRepository.findById(servicoDTO.getIdCarro()).get());
		}

		return servico;
	}

	public String salvar(ServicoDTO servicoDTO) throws EmailException, MessagingException {
		Servico servico = new Servico();
		transformaDTOEmModel(servico, servicoDTO);
		servicoRepository.save(servico);
		emailService.emailTeste(servicoDTO);

		return "O servico foi feito com o id: " + servico.getIdServico();
	}

	public ServicoDTO buscarPorId(Integer idServico) throws ServicoException {
		Optional<Servico> servico = servicoRepository.findById(idServico);
		Servico servicoPrestado = new Servico();
		ServicoDTO servicoDTO = new ServicoDTO();
		if (servico.isPresent()) {
			servicoPrestado = servico.get();
			transformaModelEmDTO(servicoPrestado, servicoDTO);
			return servicoDTO;
		}
		throw new ServicoException("Servico com o id informado nao encontrado");

	}

	public void deletar(Integer idServico) {
		servicoRepository.deleteById(idServico);
	}

	public List<RelatorioDTO> relatorio() {
		return servicoRepository.relatorio();
	}

	public String atualizar(Integer idServico, ServicoDTO servicoDTO) throws ServicoException {
		Optional<Servico> servico = servicoRepository.findById(idServico);
		Servico servicoPrestado = new Servico();
		if (servico.isPresent()) {
			servicoPrestado = servico.get();

			if (servicoDTO.getServicoPrestado() != null) {
				servicoPrestado.setServicoPrestado(servicoDTO.getServicoPrestado());
			}
			if (servicoDTO.getDataServico() != null) {
				servicoPrestado.setDataServico(servicoDTO.getDataServico());
			}
			if (servicoDTO.getValor() != null) {
				servicoPrestado.setValor(servicoDTO.getValor());
			}
			servicoRepository.save(servicoPrestado);
			return "O servico com o id " + servicoPrestado.getIdServico() + " foi realizado";
		}
		throw new ServicoException("O servico nao foi realizado");
	}

	public List<ServicoDTO> buscarTodos() {
		List<Servico> listaServicoModel = servicoRepository.findAll();
		List<ServicoDTO> listaServicoDTO = new ArrayList<>();
		for (Servico servico : listaServicoModel) {
			ServicoDTO servicoDTO = new ServicoDTO();
			transformaModelEmDTO(servico, servicoDTO);
			listaServicoDTO.add(servicoDTO);
		}
		return listaServicoDTO;
	}

	public void salvarListaServico(List<ServicoDTO> listaServicoDTO) {

	}
}

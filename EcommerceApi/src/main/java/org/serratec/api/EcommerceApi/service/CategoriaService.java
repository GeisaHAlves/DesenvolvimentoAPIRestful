package org.serratec.api.EcommerceApi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.api.EcommerceApi.DTO.CategoriaDTO;
import org.serratec.api.EcommerceApi.exception.CategoriaException;
import org.serratec.api.EcommerceApi.model.Categoria;
import org.serratec.api.EcommerceApi.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;

	public Categoria toModel(Categoria categoria, CategoriaDTO categoriaDTO) {
		categoria.setNome(categoriaDTO.getNome());
		categoria.setDescricao(categoriaDTO.getDescricao());
		return categoria;
	}

	public CategoriaDTO toDTO(CategoriaDTO categoriaDTO, Categoria categoria) {
		categoriaDTO.setIdCategoria(categoria.getIdCategoria());
		categoriaDTO.setDescricao(categoria.getDescricao());
		categoriaDTO.setNome(categoria.getNome());
		return categoriaDTO;
	}

	public String salvar(CategoriaDTO categoriaDTO) {
		Categoria categoria = new Categoria();
		categoria = toModel(categoria, categoriaDTO);
		categoriaRepository.save(categoria);
		return "Novo categoria cadastrada.\nNome: " + categoria.getNome() + "\nID categoria: "
				+ categoria.getIdCategoria();
	}

	public CategoriaDTO buscarPorId(Integer idCategoria) throws CategoriaException {
		Optional<Categoria> funOptional = categoriaRepository.findById(idCategoria);
		Categoria categoria = new Categoria();
		CategoriaDTO categoriaDTO = new CategoriaDTO();

		if (funOptional.isPresent()) {
			categoria = funOptional.get();
			categoriaDTO = toDTO(categoriaDTO, categoria);
			return categoriaDTO;
		}
		throw new CategoriaException("Categoria não encontrada.");
	}

	public String delete(Integer idCategoria) throws CategoriaException {
		Optional<Categoria> categoriaOptional = categoriaRepository.findById(idCategoria);

		if (categoriaOptional.isPresent()) {
			categoriaRepository.deleteById(idCategoria);
			return "Categoria deletada com sucesso";
		}
		throw new CategoriaException("Categoria não encontrada.");
		
	}

	public String atualizar(Integer idCategoria, CategoriaDTO categoriaDTO) throws CategoriaException {
		Optional<Categoria> funOptional = categoriaRepository.findById(idCategoria);
		Categoria categoria = new Categoria();

		if (funOptional.isPresent()) {
			categoria = funOptional.get();
			if (categoriaDTO.getDescricao() != null) {
				categoria.setDescricao(categoriaDTO.getDescricao());
			}
			if (categoriaDTO.getNome() != null) {
				categoria.setNome(categoriaDTO.getNome());
			}
			categoriaRepository.save(categoria);
			return "Categoria " + categoria.getNome() + " foi atualizada.";
		}
		throw new CategoriaException("A categoria não foi atualizada");
	}

	public List<CategoriaDTO> todosCategorias() {
		List<Categoria> lisCategorias = categoriaRepository.findAll();
		List<CategoriaDTO> categoriaDTOs = new ArrayList<>();

		for (Categoria categoria : lisCategorias) {
			CategoriaDTO categoriaDTO = new CategoriaDTO();
			categoriaDTO = toDTO(categoriaDTO, categoria);
			categoriaDTOs.add(categoriaDTO);
		}
		return categoriaDTOs;
	}

	public void salvarListaCategoria(List<CategoriaDTO> listaCategoriaDTO) {
		for (CategoriaDTO categoriaDTO : listaCategoriaDTO) {
			Categoria categoria = new Categoria();
			categoria = toModel(categoria, categoriaDTO);
			categoriaRepository.save(categoria);
		}
	}
}

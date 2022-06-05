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
		toModel(categoria, categoriaDTO);
		categoriaRepository.save(categoria);
		return "Novo categoria cadastrado.\nNome: " + categoria.getNome() + "\nID categoria: "
				+ categoria.getIdCategoria();
	}

	public CategoriaDTO buscarPorId(Integer idCategoria) throws CategoriaException {
		Optional<Categoria> funOptional = categoriaRepository.findById(idCategoria);
		Categoria categoria = new Categoria();
		CategoriaDTO categoriaDTO = new CategoriaDTO();

		if (funOptional.isPresent()) {
			categoria = funOptional.get();
			toDTO(categoriaDTO, categoria);
			return categoriaDTO;
		}
		throw new CategoriaException("Categoria não encontrada.");
	}

	public void delete(Integer idCategoria) {
		categoriaRepository.deleteById(idCategoria);
	}

	public String atualizar(Integer idCategoria, CategoriaDTO categoriaDTO) throws CategoriaException {
		Optional<Categoria> funOptional = categoriaRepository.findById(idCategoria);
		Categoria categoria = new Categoria();

		if (funOptional.isPresent()) {
			categoria = funOptional.get();
			if (categoriaDTO.getDescricao() != null) {
				categoria.setDescricao(categoria.getDescricao());
			}
			if (categoriaDTO.getNome() != null) {
				categoria.setNome(categoria.getNome());
			}
			categoriaRepository.save(categoria);
			return "Categoria " + categoria.getNome() + " foi atualizado.";
		}
		throw new CategoriaException("O categoria não foi atualizado");
	}

	public List<CategoriaDTO> todosCategorias() {
		List<Categoria> lisCategorias = categoriaRepository.findAll();
		List<CategoriaDTO> categoriaDTOs = new ArrayList<CategoriaDTO>();

		for (Categoria categoria : lisCategorias) {
			CategoriaDTO categoriaDTO = new CategoriaDTO();
			toDTO(categoriaDTO, categoria);
			categoriaRepository.save(categoria);
		}
		return categoriaDTOs;
	}

	public void salvarListaCategoria(List<CategoriaDTO> listaCategoriaDTO) {
		for (CategoriaDTO categoriaDTO : listaCategoriaDTO) {
			Categoria categoria = new Categoria();
			toModel(categoria, categoriaDTO);
			categoriaRepository.save(categoria);
		}
	}
}

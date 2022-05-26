package org.serratec.backend.ProjetoBiblioteca.service;

import java.util.List;
import java.util.Optional;

import org.serratec.backend.ProjetoBiblioteca.DTO.LivroDTO;
import org.serratec.backend.ProjetoBiblioteca.model.Livro;
import org.serratec.backend.ProjetoBiblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

	@Autowired
	LivroRepository livroRepository;

	public void salvar(LivroDTO livroDTO) {
		Livro livro = new Livro();
		transformarLivroDTOEmLivroModel(livroDTO, livro);
		livroRepository.save(livro);

	}

	public Livro transformarLivroDTOEmLivroModel(LivroDTO livroDTO, Livro livro) {

//	livro.setIdLivro(livroDTO.getIdLivro());
		livro.setTitulo(livroDTO.getTitulo());
		livro.setTipo(livroDTO.getTipo());
		livro.setAutor(livroDTO.getAutor());
		livro.setDataPublicacao(livroDTO.getDataPublicacao());

		return livro;
	}

	public LivroDTO transformarLivroModelEmLivroDTO(LivroDTO livroDTO, Livro livro) {
		livroDTO.setIdLivro(livro.getIdLivro());
		livroDTO.setTitulo(livro.getTitulo());
		livroDTO.setTipo(livro.getTipo());
		livroDTO.setAutor(livro.getAutor());
		livroDTO.setDataPublicacao(livro.getDataPublicacao());

		return livroDTO;
	}

	public LivroDTO buscarPorId(Integer idLivro) {
		Optional<Livro> livro = livroRepository.findById(idLivro);
		LivroDTO livroDTO = new LivroDTO();
		if (livro.isPresent()) {
			livroDTO = transformarLivroModelEmLivroDTO(livroDTO, livro.get());
		}
		return livroDTO;
	}

	public void atualizar(Integer idLivro, LivroDTO livroDTO) {
		Optional<Livro> livro = livroRepository.findById(idLivro);
		Livro livroNaBiblioteca = new Livro();

		if (livro.isPresent()) {
			livroNaBiblioteca = livro.get();
			if (livroDTO.getTitulo() != null) {
				livroNaBiblioteca.setTitulo(livroDTO.getTitulo());
			}
			if (livroDTO.getTipo() != null) {
				livroNaBiblioteca.setTipo(livroDTO.getTipo());
			}
			if (livroDTO.getAutor() != null) {
				livroNaBiblioteca.setAutor(livroDTO.getAutor());
			}
			if (livroDTO.getDataPublicacao() != null) {
				livroNaBiblioteca.setDataPublicacao(livroDTO.getDataPublicacao());
			}
			livroRepository.save(livroNaBiblioteca);
		}
	}

	public void delete(Integer idLivro) {
		livroRepository.deleteById(idLivro);
	}

	public List<Livro> listarTodos(String field) {
		return livroRepository.findAll(Sort.by(Sort.Direction.ASC, field));
	}

	public void salvarTodos(List<Livro> listaLivro) {
		livroRepository.saveAll(listaLivro);
	}
}

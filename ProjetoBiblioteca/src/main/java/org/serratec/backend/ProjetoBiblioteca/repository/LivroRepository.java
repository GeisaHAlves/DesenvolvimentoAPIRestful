package org.serratec.backend.ProjetoBiblioteca.repository;

import org.serratec.backend.ProjetoBiblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro,Integer>{
	
}

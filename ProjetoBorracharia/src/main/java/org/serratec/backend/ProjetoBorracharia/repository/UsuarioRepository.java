package org.serratec.backend.ProjetoBorracharia.repository;

import org.serratec.backend.ProjetoBorracharia.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


	@Repository
	public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
}

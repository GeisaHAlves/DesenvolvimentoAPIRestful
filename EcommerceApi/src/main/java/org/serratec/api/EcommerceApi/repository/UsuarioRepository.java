package org.serratec.api.EcommerceApi.repository;

import java.util.Optional;

import org.serratec.api.EcommerceApi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
	
	@Query(value="FROM Usuario u WHERE u.username = ?1")
	Optional<Usuario> buscarPorLogin(String login);

}

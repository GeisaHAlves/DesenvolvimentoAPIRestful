package org.serratec.api.EcommerceApi.security;

import java.util.ArrayList;
import java.util.Optional;

import org.serratec.api.EcommerceApi.model.Usuario;
import org.serratec.api.EcommerceApi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetalheService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.buscarPorLogin(username);

		if (usuario.isPresent()) {
			Usuario u = usuario.get();
			return new User(u.getUsername(), u.getPassword(), new ArrayList<>());
		}
		throw new UsernameNotFoundException("Usuario incorreto");
	}
	
	public void delete(Integer idUsuario) {
		usuarioRepository.deleteById(idUsuario);
	}

	public UsuarioRepository getUsuarioRepository() {
		return usuarioRepository;
	}

	public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
}

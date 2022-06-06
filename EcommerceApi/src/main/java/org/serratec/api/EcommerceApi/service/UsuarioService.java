package org.serratec.api.EcommerceApi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.serratec.api.EcommerceApi.DTO.UsuarioDTO;
import org.serratec.api.EcommerceApi.model.Usuario;
import org.serratec.api.EcommerceApi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PasswordEncoder encoder;

	private UsuarioDTO mapToDTO(Usuario usuario, UsuarioDTO usuarioDTO) {
		usuarioDTO.setIdUsuario(usuario.getIdUsuario());
		usuarioDTO.setUsername(usuario.getUsername());
		usuarioDTO.setPassword(usuario.getPassword());
		return usuarioDTO;
	}

	private Usuario mapToModel(Usuario usuario, UsuarioDTO usuarioDTO) {
		usuario.setUsername(usuarioDTO.getUsername());
		usuario.setPassword(encoder.encode(usuarioDTO.getPassword()));
		return usuario;
	}

		
	public UsuarioDTO buscarPorId(Integer idUsuario) {
		return usuarioRepository.findById(idUsuario)
				.map(usuario -> mapToDTO(usuario, new UsuarioDTO()))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	

	public List<UsuarioDTO> buscarTodos(){
		return usuarioRepository.findAll()
				.stream()
				.map(usuario -> mapToDTO(usuario, new UsuarioDTO()))
				.collect(Collectors.toList());
	}
	
	public UsuarioDTO buscarPorLogin(String username) {
		return usuarioRepository.findAll()
				.stream()
				.filter(usuario -> usuario.getUsername().equals(username))
				.map(usuario -> mapToDTO(usuario, new UsuarioDTO()))
				.findFirst()
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	public Integer salvar(UsuarioDTO usuarioDTO) {
		Usuario usuario = new Usuario();
		mapToModel(usuario, usuarioDTO);
		usuarioRepository.save(usuario);
		return usuario.getIdUsuario();
		
	}

}

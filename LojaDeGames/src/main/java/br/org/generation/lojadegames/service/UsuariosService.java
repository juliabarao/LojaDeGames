package br.org.generation.lojadegames.service;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import br.org.generation.lojadegames.model.Usuarios;
import br.org.generation.lojadegames.model.UsuariosLogin;
import br.org.generation.lojadegames.repository.UsuariosRepository;

@Service
public class UsuariosService {
	
	@Autowired
	private UsuariosRepository usuariosRepository;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	public String encoder(String senha) {
		
		return encoder.encode(senha);
		
	}
	
	public List<Usuarios> listarUsuarios() {
		
		return usuariosRepository.findAll();
		
	}
	
	public Optional <Usuarios> buscarUsuarios(long id) {
		
		return usuariosRepository.findById(id);
		
	}
	
	public Optional <Usuarios> cadastrarUsuarios(Usuarios usuarios){
		
		if(usuariosRepository.findByUsuarios(usuarios.getUsuarios()).isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O usuário já existe!", null);
		
		int idade = Period.between(usuarios.getDataNascimento(), LocalDate.now()).getYears();
		
		if(idade < 18)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O usuário é menor de idade!", null);
		
		
		usuarios.setSenha(encoder(usuarios.getSenha()));
		
		return Optional.of(usuariosRepository.save(usuarios));
		
	}
	
	public Optional <Usuarios> atualizarUsuarios(Usuarios usuarios){
		
		if(usuariosRepository.findByUsuarios(usuarios.getUsuarios()).isPresent()) {
		
			int idade = Period.between(usuarios.getDataNascimento(), LocalDate.now()).getYears();
			
			if(idade < 18)
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O usuário é menor de idade!", null);
			
			usuarios.setSenha(encoder(usuarios.getSenha()));
			
			return Optional.of(usuariosRepository.save(usuarios));
			
		}else {
		
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O usuário já existe!", null);
		
		}
	}
	
	public Optional<UsuariosLogin> loginUsuarios(Optional<UsuariosLogin> usuariosLogin) {
		Optional <Usuarios> usuarios = usuariosRepository.findByUsuarios(usuariosLogin.get().getUsuarios());
		
		if(usuarios.isPresent()) {
			
			if(encoder.matches(usuariosLogin.get().getSenha(), usuarios.get().getSenha())) {
				
				String auth = usuariosLogin.get().getSenha() + ":" + usuariosLogin.get().getSenha();
				byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodeAuth);
				
				usuariosLogin.get().setId(usuarios.get().getId());
				usuariosLogin.get().setNome(usuarios.get().getNome());
				usuariosLogin.get().setSenha(usuarios.get().getSenha());
				usuariosLogin.get().setToken(authHeader);
				
				return usuariosLogin;
				
			}
		}
		
		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário ou Senha Inválidos!", null);
		
	}

}

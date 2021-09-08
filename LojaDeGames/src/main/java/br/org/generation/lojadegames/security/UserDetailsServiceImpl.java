package br.org.generation.lojadegames.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.org.generation.lojadegames.model.Usuarios;
import br.org.generation.lojadegames.repository.UsuariosRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsuariosRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		Optional<Usuarios> usuario = userRepository.findByUsuarios(userName);
		usuario.orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));
		return usuario.map(UserDetailsImpl::new).get();
	}

}

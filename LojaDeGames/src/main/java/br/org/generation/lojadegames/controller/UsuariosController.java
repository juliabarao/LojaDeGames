package br.org.generation.lojadegames.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.lojadegames.model.Usuarios;
import br.org.generation.lojadegames.model.UsuariosLogin;
import br.org.generation.lojadegames.service.UsuariosService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuariosController {
	
	@Autowired
	private UsuariosService usuariosService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Usuarios>> getAll(){
		
		return ResponseEntity.ok(usuariosService.listarUsuarios());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuarios> getById(@PathVariable long id){
		
		return usuariosService.buscarUsuarios(id)
				.map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@PostMapping("/logar")
	public ResponseEntity<UsuariosLogin> loginUsuarios(@RequestBody Optional <UsuariosLogin> usuariosLogin){
		
		return usuariosService.loginUsuarios(usuariosLogin)
			.map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
			.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());

	}
		
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuarios> postUsuarios(@RequestBody Usuarios usuarios) {
		
		return usuariosService.cadastrarUsuarios(usuarios)
				.map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
		
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Usuarios> putUsuarios(@RequestBody Usuarios usuarios){
		
		return usuariosService.atualizarUsuarios(usuarios)
			.map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
			.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
        
	}

}

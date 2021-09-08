package br.org.generation.lojadegames.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.generation.lojadegames.model.Usuarios;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Long>{
	
	public Optional<Usuarios> findByUsuarios(String usuarios);

	public List<Usuarios> findAllByNomeContainingIgnoreCase(String nome);

	public Usuarios findByNome(String nome);


}

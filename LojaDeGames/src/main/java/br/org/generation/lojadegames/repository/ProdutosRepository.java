package br.org.generation.lojadegames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.org.generation.lojadegames.model.Produtos;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Long> {
	
	public List<Produtos> findAllByNomeContainingIgnoreCase(String nome);
	
	@Query(value = "select count(categoria_id) from tb_produtos where categoria_id = :id", nativeQuery = true)
	public int contarProdutos(@Param("id") long id);

}
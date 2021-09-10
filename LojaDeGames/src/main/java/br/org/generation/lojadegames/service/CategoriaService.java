package br.org.generation.lojadegames.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.generation.lojadegames.model.Categoria;
import br.org.generation.lojadegames.repository.CategoriaRepository;
import br.org.generation.lojadegames.repository.ProdutosRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutosRepository produtosRepository;

	public List<Categoria> trendProducts(){
		
		List<Categoria> categorias = categoriaRepository.findAll();
		
		for (Categoria categoria : categorias) {
	
			categoria.setNumeroProdutos(produtosRepository.contarProdutos(categoria.getId()));
		}
		
		Collections.sort(categorias, Collections.reverseOrder(Comparator.comparing(Categoria::getNumeroProdutos)));
		return categorias;
	}
}
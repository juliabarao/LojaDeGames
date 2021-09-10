package br.org.generation.lojadegames.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.org.generation.lojadegames.model.Produtos;
import br.org.generation.lojadegames.repository.ProdutosRepository;

@Service
public class ProdutosService {
	
	@Autowired
	private ProdutosRepository produtosRepository;

	public Produtos curtir(Long id) {

		Produtos produtos = buscarProdutoPeloId(id);

		produtos.setCurtidos(produtos.getCurtidos() + 1);

		return produtosRepository.save(produtos);

	}

	public Produtos descurtir(Long id) {

		Produtos produtos = buscarProdutoPeloId(id);

		if (produtos.getCurtidos() > 0) {

			produtos.setCurtidos(produtos.getCurtidos() - 1);

		} else {

			produtos.setCurtidos(0);

		}

		return produtosRepository.save(produtos);

	}

	private Produtos buscarProdutoPeloId(Long id) {

		Produtos produtosSalvo = produtosRepository.findById(id).orElse(null);

		if (produtosSalvo == null) {

			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrada!", null);
		}

		return produtosSalvo;
	}

}

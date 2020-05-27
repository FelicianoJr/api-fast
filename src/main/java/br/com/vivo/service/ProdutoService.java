package br.com.vivo.service;

import br.com.vivo.exception.NaoEncontradoException;
import br.com.vivo.model.Produto;
import br.com.vivo.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public Produto buscarPorId(Long id) {
		return produtoRepository.findById(id).orElseThrow(NaoEncontradoException::new);
	}

	public Produto buscarPorNumeroProduto(String numeroProduto) {
		return produtoRepository.findByNumeroProduto(numeroProduto).orElseThrow(NaoEncontradoException::new);
	}

}

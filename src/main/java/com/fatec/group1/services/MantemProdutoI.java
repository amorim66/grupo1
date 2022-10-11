package com.fatec.group1.services;

import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fatec.group1.model.Produto;
import com.fatec.group1.model.ProdutoRepository;


@Service
public class MantemProdutoI implements MantemProduto {
    Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	ProdutoRepository repository;

    @Override
    public List<Produto> consultaTodos() {
        logger.info(">>>>>> servico consultaTodos chamado");
		return repository.findAll();
    }

    @Override
    public Optional<Produto> consultaPorId(Long id) {
        logger.info(">>>>>> servico consultaPorId chamado");
		return repository.findById(id);
    }

    @Override
    public Optional<Produto> save(Produto produto) {
        return Optional.ofNullable(repository.save(produto));
    }

    @Override
    public void delete(Long id) {
        logger.info(">>>>>> servico delete por id chamado");
		repository.deleteById(id);
    }

    @Override
    public Optional<Produto> atualiza(Produto produto) {
        logger.info(">>>>>> 1.servico altera produto chamado");
		Optional<Produto> umProduto = consultaPorId(produto.getId());
        if (umProduto.isPresent()) {
            Produto produtoMod = new Produto(produto.getDescricao(), produto.getValorVenda(), produto.getCategoria(), produto.getMarca(), produto.getQuantidadeEstoque());
            produtoMod.setId(produto.getId());
            return Optional.ofNullable(repository.save(produtoMod));
        } else {
            return Optional.empty();
        }
    }
    
}

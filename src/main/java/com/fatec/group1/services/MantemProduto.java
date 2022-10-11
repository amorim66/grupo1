package com.fatec.group1.services;

import java.util.List;
import java.util.Optional;
import com.fatec.group1.model.Produto;

public interface MantemProduto {
    List<Produto> consultaTodos();

    Optional<Produto> consultaPorId(Long id);

	Optional<Produto> save(Produto produto);

	void delete(Long id);

	Optional<Produto> atualiza(Produto produto);
}

package com.sistema.cadastro.usecases.product;

import com.sistema.cadastro.domain.entities.ProdutoRecord;
import com.sistema.cadastro.usecases.ports.ProdutoRepository;

import java.util.List;


public class ListarProdutosUseCase {
    
    private final ProdutoRepository repository;
    
    public ListarProdutosUseCase(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<ProdutoRecord> executar(){
        // Stream Api processando a coleção de forma fluida e funcional

        return repository.listarTodos()
                .stream()
                .toList();
    }


}

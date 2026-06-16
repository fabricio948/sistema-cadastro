package com.sistema.cadastro.usecases.ports;


import com.sistema.cadastro.domain.entities.ProdutoRecord;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProdutoRepository {
    void salvar(ProdutoRecord produto);
    List<ProdutoRecord> listarTodos();
    Optional<ProdutoRecord> buscarPorId(UUID id);
} 

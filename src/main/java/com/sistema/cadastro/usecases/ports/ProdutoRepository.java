package com.sistema.cadastro.usecases.ports;

import com.sistema.cadastro.domain.entities.ProdutoRecord;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Interface de repositório para operações com produtos.
 * Aplica o padrão Repository para abstrair o acesso a dados.
 */
public interface ProdutoRepository {
    void salvar(ProdutoRecord produto);
    List<ProdutoRecord> listarTodos();
    Optional<ProdutoRecord> buscarPorId(UUID id);
}

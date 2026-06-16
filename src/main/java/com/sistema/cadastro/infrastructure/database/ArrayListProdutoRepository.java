package com.sistema.cadastro.infrastructure.database;

import com.sistema.cadastro.domain.entities.ProdutoRecord;
import com.sistema.cadastro.usecases.ports.ProdutoRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementação do repositório utilizando ArrayList como simulação de banco de dados.
 * Aplica o padrão Repository para abstrair o acesso a dados.
 */
public final class ArrayListProdutoRepository implements ProdutoRepository {

    private final List<ProdutoRecord> bancoDeDados = new ArrayList<>();

    @Override
    public void salvar(ProdutoRecord produto) {
        bancoDeDados.add(produto);
    }

    @Override
    public List<ProdutoRecord> listarTodos() {
        return Collections.unmodifiableList(bancoDeDados);
    }

    @Override
    public Optional<ProdutoRecord> buscarPorId(UUID id) {
        // Utilizado Stream API de forma fluida conforme boas práticas
        return bancoDeDados.stream()
                .filter(produto -> produto.id().equals(id))
                .findFirst();
    }
}

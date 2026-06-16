package com.sistema.cadastro.infrastructure.database;

import com.sistema.cadastro.domain.entities.ProdutoRecord;
import com.sistema.cadastro.usecases.ports.ProdutoRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


// Implementação obrigatória da interface selada
public final class ArrayListProdutoRepository implements ProdutoRepository {

    // simulação do banco de dados ultilizando ArrayList
    private final List<ProdutoRecord> bancoDeDados = new ArrayList<>();

    @Override
    public void salvar(ProdutoRecord produto){
        bancoDeDados.add(produto);

    }

    @Override
    public Optional<ProdutoRecord> buscarPorId(UUID id){
        // utilizado Stream API de forma fluida conforme boas praticas 

        return bancoDeDados.stream()
                .filter(produto -> produto.id().equals(id))
                .findFirst();
                
    }

    @Override
    public List<ProdutoRecord> listarTodos() {
        return Collections.unmodifiableList(bancoDeDados);
    }
    
}

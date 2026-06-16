package com.sistema.cadastro.usecases.product;

import com.sistema.cadastro.domain.entities.ProdutoRecord;
import com.sistema.cadastro.domain.enums.TaxaTipo;
import com.sistema.cadastro.usecases.ports.ProdutoRepository;

import java.time.LocalDate;
import java.util.UUID;

public class CadastrarProdutoUseCase {

    // Injecao de dependencia puramente via construtor
    private final ProdutoRepository repository;

    public CadastrarProdutoUseCase(ProdutoRepository repository) {
        this.repository = repository;
    }

    public void executar(String nome, String descricao, double precoBase, String tipoTaxa) {

        // Switch expression moderna para mapear a taxa de forma segura
        TaxaTipo taxa = switch (tipoTaxa.toUpperCase()) {
            case "BASIC" -> TaxaTipo.BASIC;
            case "MEDIA" -> TaxaTipo.MEDIA;
            case "ALTA" -> TaxaTipo.ALTA;
            default -> throw new IllegalArgumentException("Tipo de taxa inválido! Escolha BASIC, MEDIA ou ALTA.");
        };

        // Criação do Record com UUID automatico e data atual
        ProdutoRecord novoProduto = new ProdutoRecord(
            UUID.randomUUID(),
            nome,
            descricao,
            precoBase,
            taxa,
            LocalDate.now()
        );

        repository.salvar(novoProduto);
    }
}

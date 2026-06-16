package com.sistema.cadastro.presentation.controller;

import com.sistema.cadastro.domain.entities.ProdutoRecord;
import com.sistema.cadastro.usecases.product.CadastrarProdutoUseCase;
import com.sistema.cadastro.usecases.product.ListarProdutosUseCase;

import java.util.List;

public class ProdutoController {

    private final CadastrarProdutoUseCase cadastrarUseCase;
    private final ListarProdutosUseCase listarUseCase;

    // Injeção de dependência manual via construtor
    public ProdutoController(CadastrarProdutoUseCase cadastrarUseCase, ListarProdutosUseCase listarUseCase) {
        this.cadastrarUseCase = cadastrarUseCase;
        this.listarUseCase = listarUseCase;
    }
    
    public void cadastrar(String nome, String descricao, double precoBase, String tipoTaxa) {
        if (nome == null || nome.isBlank()) {
            System.out.println("❌ Erro: O nome do produto não pode ser vazio.");
            return;
        }
        if (precoBase <= 0) {
            System.out.println("❌ Erro: O preço base deve ser maior que zero.");
            return;
        }
        
        try {
            cadastrarUseCase.executar(nome, descricao, precoBase, tipoTaxa);
            System.out.println("✅ Produto cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }

    public List<ProdutoRecord> listar() {
        return listarUseCase.executar();
    }
}

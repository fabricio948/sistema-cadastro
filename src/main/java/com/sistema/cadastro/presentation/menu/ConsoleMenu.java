package com.sistema.cadastro.presentation.menu;

import com.sistema.cadastro.domain.entities.ProdutoRecord;
import com.sistema.cadastro.presentation.controller.ProdutoController;

import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {
    
    private final ProdutoController controller;
    private final Scanner scanner;

    public ConsoleMenu(ProdutoController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void exibir() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== SISTEMA DE CADASTRO DE PRODUTOS ===");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                processarOpcao(opcao);
            } catch (NumberFormatException e) {
                System.out.println("❌ Opção inválida! Digite apenas números.");
            }
        }
    }

     private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1 -> iniciarCadastro();
            case 2 -> iniciarListagem();
            case 0 -> System.out.println("Saindo do sistema... Até logo!");
            default -> System.out.println("❌ Opção desconhecida.");
        }
    }

    private void iniciarCadastro() {
        System.out.print("Nome do Produto: ");
        String nome = scanner.nextLine();

        System.out.print("Descrição do Produto: ");
        String descricao = scanner.nextLine();

        System.out.print("Preço Base: R$ ");
        double precoBase = Double.parseDouble(scanner.nextLine());

        System.out.println("Selecione a Taxa aplicável:");
        System.out.println("- BASIC (3.0%)");
        System.out.println("- MEDIA (8.5%)");
        System.out.println("- ALTA (10.5%)");
        System.out.print("Digite o tipo da taxa: ");
        String tipoTaxa = scanner.nextLine();

        controller.cadastrar(nome, descricao, precoBase, tipoTaxa);
    }

    private void iniciarListagem() {
        List<ProdutoRecord> produtos = controller.listar();

        if (produtos.isEmpty()) {
            System.out.println("⚠️ Nenhum produto cadastrado até o momento.");
            return;
        }

        System.out.println("\n--- LISTA DE PRODUTOS CADASTRADOS ---");
        // Stream API para percorrer a exibição de forma moderna e limpa
        produtos.forEach(p -> {
            System.out.printf("ID: %s | Nome: %s\n", p.id(), p.nome());
            System.out.printf("Preço Base: R$ %.2f | Taxa: %s (Imposto: R$ %.2f)\n", p.precoBase(), p.taxa(), p.obterValorDoImposto());
            System.out.printf("Preço Final: R$ %.2f | Cadastrado em: %s\n", p.obterPrecoFinal(), p.dataCadastro());
            System.out.println("-----------------------------------------------------------------");
        });
    }
}

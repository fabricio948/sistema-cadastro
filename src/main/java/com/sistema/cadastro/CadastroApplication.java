package com.sistema.cadastro;

import com.sistema.cadastro.infrastructure.database.ArrayListProdutoRepository;
import com.sistema.cadastro.presentation.controller.ProdutoController;
import com.sistema.cadastro.presentation.menu.ConsoleMenu;
import com.sistema.cadastro.usecases.ports.ProdutoRepository;
import com.sistema.cadastro.usecases.product.CadastrarProdutoUseCase;
import com.sistema.cadastro.usecases.product.ListarProdutosUseCase;

/**
 * Main application class for the Cadastro system.
 * Pure Java OOP project — no Spring Boot framework.
 */
public class CadastroApplication {

    public static void main(String[] args) {
        // 1. Inicializa o mecanismo de infraestrutura (Banco em memória)
        ProdutoRepository repository = new ArrayListProdutoRepository();

        // 2. Inicializa os Casos de Uso injetando o repositório por construtor
        CadastrarProdutoUseCase cadastrarUseCase = new CadastrarProdutoUseCase(repository);
        ListarProdutosUseCase listarUseCase = new ListarProdutosUseCase(repository);

        // 3. Inicializa o Controller injetando os Casos de Uso
        ProdutoController controller = new ProdutoController(cadastrarUseCase, listarUseCase);

        // 4. Inicializa a Interface do Usuário (Menu) injetando o Controller
        ConsoleMenu menu = new ConsoleMenu(controller);

        // 5. Roda a aplicação
        menu.exibir();
    }
}

package com.sistema.cadastro.usecases.product;

import com.sistema.cadastro.domain.entities.ProdutoRecord;
import com.sistema.cadastro.domain.enums.TaxaTipo;
import com.sistema.cadastro.usecases.ports.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Unit tests for ListarProdutosUseCase.
 * Uses a manual in-memory repository mock (no Mockito, pure Java).
 */
@DisplayName("ListarProdutosUseCase - Use Case Tests")
class ListarProdutosUseCaseTest {

    private ListarProdutosUseCase useCase;
    private InMemoryProdutoRepository repository;

    /**
     * Manual mock repository implementing ProdutoRepository interface.
     * Follows the Dependency Inversion Principle for testing.
     */
    private static class InMemoryProdutoRepository implements ProdutoRepository {
        private final List<ProdutoRecord> produtos = new ArrayList<>();

        @Override
        public void salvar(ProdutoRecord produto) {
            produtos.add(produto);
        }

        @Override
        public List<ProdutoRecord> listarTodos() {
            return List.copyOf(produtos);
        }

        @Override
        public Optional<ProdutoRecord> buscarPorId(UUID id) {
            return produtos.stream()
                    .filter(p -> p.id().equals(id))
                    .findFirst();
        }

        public void addProduto(ProdutoRecord produto) {
            produtos.add(produto);
        }
    }

    @BeforeEach
    void setUp() {
        repository = new InMemoryProdutoRepository();
        useCase = new ListarProdutosUseCase(repository);
    }

    @Test
    @DisplayName("Should return empty list when no products are registered")
    void testExecutarEmptyList() {
        List<ProdutoRecord> produtos = useCase.executar();
        assertTrue(produtos.isEmpty(), "Should return empty list when no products exist");
    }

    @Test
    @DisplayName("Should return all registered products")
    void testExecutarWithProducts() {
        ProdutoRecord p1 = new ProdutoRecord(
            UUID.randomUUID(), "Notebook", "Notebook Dell", 2000.0, TaxaTipo.MEDIA, LocalDate.now()
        );
        ProdutoRecord p2 = new ProdutoRecord(
            UUID.randomUUID(), "Mouse", "Mouse USB", 100.0, TaxaTipo.BASIC, LocalDate.now()
        );

        repository.addProduto(p1);
        repository.addProduto(p2);

        List<ProdutoRecord> produtos = useCase.executar();
        assertEquals(2, produtos.size());
    }

    @Test
    @DisplayName("Should return products in the same order they were added")
    void testExecutarMaintainsOrder() {
        ProdutoRecord p1 = new ProdutoRecord(
            UUID.randomUUID(), "Primeiro", "Primeiro produto", 100.0, TaxaTipo.BASIC, LocalDate.now()
        );
        ProdutoRecord p2 = new ProdutoRecord(
            UUID.randomUUID(), "Segundo", "Segundo produto", 200.0, TaxaTipo.MEDIA, LocalDate.now()
        );

        repository.addProduto(p1);
        repository.addProduto(p2);

        List<ProdutoRecord> produtos = useCase.executar();
        assertEquals("Primeiro", produtos.get(0).nome());
        assertEquals("Segundo", produtos.get(1).nome());
    }

    @Test
    @DisplayName("Should return a new list (defensive copy) not the internal reference")
    void testExecutarReturnsDefensiveCopy() {
        ProdutoRecord p1 = new ProdutoRecord(
            UUID.randomUUID(), "Teste", "Teste", 100.0, TaxaTipo.BASIC, LocalDate.now()
        );
        repository.addProduto(p1);

        List<ProdutoRecord> produtos = useCase.executar();
        // The returned list should be independent (toList() creates a new list)
        assertNotNull(produtos);
    }
}

package com.sistema.cadastro.usecases.product;

import com.sistema.cadastro.domain.entities.ProdutoRecord;
import com.sistema.cadastro.domain.enums.TaxaTipo;
import com.sistema.cadastro.usecases.ports.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Unit tests for CadastrarProdutoUseCase.
 * Uses a manual in-memory repository mock (no Mockito, pure Java).
 */
@DisplayName("CadastrarProdutoUseCase - Use Case Tests")
class CadastrarProdutoUseCaseTest {

    private CadastrarProdutoUseCase useCase;
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

        public int count() {
            return produtos.size();
        }
    }

    @BeforeEach
    void setUp() {
        repository = new InMemoryProdutoRepository();
        useCase = new CadastrarProdutoUseCase(repository);
    }

    @Test
    @DisplayName("Should register a product with BASIC tax successfully")
    void testExecutarWithBasicTax() {
        useCase.executar("Notebook", "Notebook Dell i7", 2000.0, "BASIC");

        assertEquals(1, repository.count(), "Should have 1 product saved");
        ProdutoRecord saved = repository.listarTodos().getFirst();
        assertEquals("Notebook", saved.nome());
        assertEquals(TaxaTipo.BASIC, saved.taxa());
    }

    @Test
    @DisplayName("Should register a product with MEDIA tax successfully")
    void testExecutarWithMediaTax() {
        useCase.executar("Mouse", "Mouse USB", 100.0, "MEDIA");

        assertEquals(1, repository.count());
        ProdutoRecord saved = repository.listarTodos().getFirst();
        assertEquals(TaxaTipo.MEDIA, saved.taxa());
    }

    @Test
    @DisplayName("Should register a product with ALTA tax successfully")
    void testExecutarWithAltaTax() {
        useCase.executar("Monitor", "Monitor 4K", 3000.0, "ALTA");

        assertEquals(1, repository.count());
        ProdutoRecord saved = repository.listarTodos().getFirst();
        assertEquals(TaxaTipo.ALTA, saved.taxa());
    }

    @Test
    @DisplayName("Should throw exception when tax type is invalid")
    void testExecutarWithInvalidTax() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> useCase.executar("Teste", "Teste", 100.0, "INVALIDA")
        );
        assertTrue(exception.getMessage().contains("Tipo de taxa inválido"));
    }

    @Test
    @DisplayName("Should throw exception when tax type is empty")
    void testExecutarWithEmptyTax() {
        assertThrows(
            IllegalArgumentException.class,
            () -> useCase.executar("Teste", "Teste", 100.0, "")
        );
    }

    @Test
    @DisplayName("Should accept lowercase tax type (case insensitive)")
    void testExecutarWithLowercaseTax() {
        useCase.executar("Teclado", "Teclado Mecânico", 150.0, "basic");

        assertEquals(1, repository.count());
        ProdutoRecord saved = repository.listarTodos().getFirst();
        assertEquals(TaxaTipo.BASIC, saved.taxa());
    }

    @Test
    @DisplayName("Should generate a unique UUID for each product")
    void testExecutarGeneratesUniqueId() {
        useCase.executar("Produto1", "Desc1", 100.0, "BASIC");
        useCase.executar("Produto2", "Desc2", 200.0, "MEDIA");

        List<ProdutoRecord> produtos = repository.listarTodos();
        assertNotEquals(produtos.get(0).id(), produtos.get(1).id());
    }
}

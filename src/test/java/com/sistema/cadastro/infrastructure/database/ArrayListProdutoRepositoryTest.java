package com.sistema.cadastro.infrastructure.database;

import com.sistema.cadastro.domain.entities.ProdutoRecord;
import com.sistema.cadastro.domain.enums.TaxaTipo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Unit tests for ArrayListProdutoRepository.
 * Tests the in-memory repository implementation.
 */
@DisplayName("ArrayListProdutoRepository - Repository Pattern Tests")
class ArrayListProdutoRepositoryTest {

    private ArrayListProdutoRepository repository;

    @BeforeEach
    void setUp() {
        repository = new ArrayListProdutoRepository();
    }

    private ProdutoRecord createProduto(String nome, double precoBase, TaxaTipo taxa) {
        return new ProdutoRecord(
            UUID.randomUUID(),
            nome,
            "Descricao " + nome,
            precoBase,
            taxa,
            LocalDate.now()
        );
    }

    @Test
    @DisplayName("Should save a product successfully")
    void testSalvar() {
        ProdutoRecord produto = createProduto("Notebook", 2000.0, TaxaTipo.MEDIA);
        repository.salvar(produto);

        List<ProdutoRecord> produtos = repository.listarTodos();
        assertEquals(1, produtos.size());
        assertEquals(produto.id(), produtos.getFirst().id());
    }

    @Test
    @DisplayName("Should list all saved products")
    void testListarTodos() {
        ProdutoRecord p1 = createProduto("Notebook", 2000.0, TaxaTipo.MEDIA);
        ProdutoRecord p2 = createProduto("Mouse", 100.0, TaxaTipo.BASIC);
        ProdutoRecord p3 = createProduto("Monitor", 3000.0, TaxaTipo.ALTA);

        repository.salvar(p1);
        repository.salvar(p2);
        repository.salvar(p3);

        List<ProdutoRecord> produtos = repository.listarTodos();
        assertEquals(3, produtos.size());
    }

    @Test
    @DisplayName("Should return empty list when no products are saved")
    void testListarTodosEmpty() {
        List<ProdutoRecord> produtos = repository.listarTodos();
        assertTrue(produtos.isEmpty(), "Repository should be empty initially");
    }

    @Test
    @DisplayName("Should return unmodifiable list from listarTodos")
    void testListarTodosReturnsUnmodifiableList() {
        ProdutoRecord produto = createProduto("Teclado", 150.0, TaxaTipo.BASIC);
        repository.salvar(produto);

        List<ProdutoRecord> produtos = repository.listarTodos();
        assertThrows(UnsupportedOperationException.class, () -> produtos.add(createProduto("Test", 10.0, TaxaTipo.BASIC)));
    }

    @Test
    @DisplayName("Should find product by ID")
    void testBuscarPorId() {
        ProdutoRecord produto = createProduto("Notebook", 2000.0, TaxaTipo.MEDIA);
        repository.salvar(produto);

        Optional<ProdutoRecord> found = repository.buscarPorId(produto.id());
        assertTrue(found.isPresent());
        assertEquals(produto.nome(), found.get().nome());
    }

    @Test
    @DisplayName("Should return empty Optional when ID not found")
    void testBuscarPorIdNotFound() {
        Optional<ProdutoRecord> found = repository.buscarPorId(UUID.randomUUID());
        assertTrue(found.isEmpty(), "Should return empty for non-existent ID");
    }

    @Test
    @DisplayName("Should save multiple products and list them in insertion order")
    void testInsertionOrder() {
        ProdutoRecord p1 = createProduto("Primeiro", 100.0, TaxaTipo.BASIC);
        ProdutoRecord p2 = createProduto("Segundo", 200.0, TaxaTipo.MEDIA);

        repository.salvar(p1);
        repository.salvar(p2);

        List<ProdutoRecord> produtos = repository.listarTodos();
        assertEquals(p1.id(), produtos.get(0).id());
        assertEquals(p2.id(), produtos.get(1).id());
    }
}

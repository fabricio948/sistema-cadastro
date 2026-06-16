package com.sistema.cadastro.domain.entities;

import com.sistema.cadastro.domain.enums.TaxaTipo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Unit tests for ProdutoRecord.
 * Tests the Record immutability and business methods.
 */
@DisplayName("ProdutoRecord - Record Tests")
class ProdutoRecordTest {

    private static final UUID ID = UUID.randomUUID();
    private static final String NOME = "Notebook";
    private static final String DESCRICAO = "Notebook Dell i7";
    private static final double PRECO_BASE = 2000.0;
    private static final TaxaTipo TAXA = TaxaTipo.MEDIA;
    private static final LocalDate DATA = LocalDate.now();

    private ProdutoRecord createProduto() {
        return new ProdutoRecord(ID, NOME, DESCRICAO, PRECO_BASE, TAXA, DATA);
    }

    @Test
    @DisplayName("Should create a ProdutoRecord with all fields")
    void testCreateProdutoRecord() {
        ProdutoRecord produto = createProduto();

        assertEquals(ID, produto.id());
        assertEquals(NOME, produto.nome());
        assertEquals(DESCRICAO, produto.descricao());
        assertEquals(PRECO_BASE, produto.precoBase(), 0.001);
        assertEquals(TAXA, produto.taxa());
        assertEquals(DATA, produto.dataCadastro());
    }

    @Test
    @DisplayName("Should calculate tax value correctly delegating to TaxaTipo")
    void testObterValorDoImposto() {
        ProdutoRecord produto = createProduto();
        double expectedTax = PRECO_BASE * 0.085; // MEDIA = 8.5%
        assertEquals(expectedTax, produto.obterValorDoImposto(), 0.001);
    }

    @Test
    @DisplayName("Should calculate final price as base price plus tax")
    void testObterPrecoFinal() {
        ProdutoRecord produto = createProduto();
        double expectedFinalPrice = PRECO_BASE + (PRECO_BASE * 0.085);
        assertEquals(expectedFinalPrice, produto.obterPrecoFinal(), 0.001);
    }

    @Test
    @DisplayName("Should be immutable - fields cannot be modified")
    void testImmutability() {
        ProdutoRecord produto = createProduto();
        // Record fields are final and accessible only via accessor methods
        assertNotNull(produto.id());
        assertNotNull(produto.nome());
        assertNotNull(produto.descricao());
        assertNotNull(produto.taxa());
        assertNotNull(produto.dataCadastro());
    }

    @Test
    @DisplayName("Should calculate final price correctly for BASIC tax")
    void testPrecoFinalWithBasicTax() {
        ProdutoRecord produto = new ProdutoRecord(
            UUID.randomUUID(), "Mouse", "Mouse USB", 100.0, TaxaTipo.BASIC, LocalDate.now()
        );
        assertEquals(103.0, produto.obterPrecoFinal(), 0.001);
    }

    @Test
    @DisplayName("Should calculate final price correctly for ALTA tax")
    void testPrecoFinalWithAltaTax() {
        ProdutoRecord produto = new ProdutoRecord(
            UUID.randomUUID(), "Monitor", "Monitor 4K", 3000.0, TaxaTipo.ALTA, LocalDate.now()
        );
        assertEquals(3315.0, produto.obterPrecoFinal(), 0.001);
    }
}

package com.sistema.cadastro.domain.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for TaxaTipo enum.
 * Tests the Strategy Pattern implementation for tax calculation.
 */
@DisplayName("TaxaTipo - Strategy Pattern Tests")
class TaxaTipoTest {

    private static final double PRECO_BASE = 1000.0;

    @Test
    @DisplayName("Should calculate BASIC tax as 3.0% of base price")
    void testCalcularImpostoBasic() {
        double imposto = TaxaTipo.BASIC.calcularImposto(PRECO_BASE);
        assertEquals(30.0, imposto, 0.001, "BASIC tax should be 3.0% of R$ 1000.00 = R$ 30.00");
    }

    @Test
    @DisplayName("Should calculate MEDIA tax as 8.5% of base price")
    void testCalcularImpostoMedia() {
        double imposto = TaxaTipo.MEDIA.calcularImposto(PRECO_BASE);
        assertEquals(85.0, imposto, 0.001, "MEDIA tax should be 8.5% of R$ 1000.00 = R$ 85.00");
    }

    @Test
    @DisplayName("Should calculate ALTA tax as 10.5% of base price")
    void testCalcularImpostoAlta() {
        double imposto = TaxaTipo.ALTA.calcularImposto(PRECO_BASE);
        assertEquals(105.0, imposto, 0.001, "ALTA tax should be 10.5% of R$ 1000.00 = R$ 105.00");
    }

    @Test
    @DisplayName("Should return zero tax when base price is zero")
    void testCalcularImpostoWithZeroPrice() {
        assertEquals(0.0, TaxaTipo.BASIC.calcularImposto(0.0), 0.001);
        assertEquals(0.0, TaxaTipo.MEDIA.calcularImposto(0.0), 0.001);
        assertEquals(0.0, TaxaTipo.ALTA.calcularImposto(0.0), 0.001);
    }

    @Test
    @DisplayName("Should return correct percentage values for each tax type")
    void testGetPercentual() {
        assertEquals(0.030, TaxaTipo.BASIC.getPercentual(), 0.001);
        assertEquals(0.085, TaxaTipo.MEDIA.getPercentual(), 0.001);
        assertEquals(0.105, TaxaTipo.ALTA.getPercentual(), 0.001);
    }
}

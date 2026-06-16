package com.sistema.cadastro.domain.entities;

import com.sistema.cadastro.domain.enums.TaxaTipo;
import java.time.LocalDate;
import java.util.UUID;

public record ProdutoRecord(
    UUID id,
    String nome,
    String descricao,
    double precoBase,
    TaxaTipo taxa,
    LocalDate dataCadastro
) {
    /**
     * Calcula o valor do imposto com base na taxa e no preço base.
     * Delega o cálculo para o enum TaxaTipo.
     */
    public double obterValorDoImposto() {
        return taxa.calcularImposto(precoBase);
    }

    /**
     * Calcula o preço final somando o imposto ao preço base.
     */
    public double obterPrecoFinal() {
        return precoBase + obterValorDoImposto();
    }
}

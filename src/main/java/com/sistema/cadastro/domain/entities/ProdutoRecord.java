package com.sistema.cadastro.domain.entities;

import com.sistema.cadastro.domain.enums.TaxaTipo;
import java.time.LocalDate;
import java.util.UUID;


public record ProdutoRecord (
    UUID id,
    String nome,
    String descricao,
    double precoBase,
    TaxaTipo taxaTipo,
    LocalDate dataCadastro,
    TaxaTipo taxa
){
    // Metodo solicitado no recor: calcula o valor do imposto insolado

    public double obterValorDoImposto(){
        return taxa.calcularImposto(precoBase);
    }
    
}

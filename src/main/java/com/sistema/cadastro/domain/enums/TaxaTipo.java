package com.sistema.cadastro.domain.enums;

public enum TaxaTipo {
    BASIC(0.030),
    MEDIA(0.085),
    ALTA(0.105);

    private final double percentual;

    TaxaTipo(double percentual){
        this.percentual = percentual;

    }
    public double getPercentual(){
        return this.percentual;
    }
    

    // Decomposicao da responsabilidade de calcular o valor exato do imposto

    public double calcularImposto(double precoBase){
        return precoBase * this.percentual;
    }
}

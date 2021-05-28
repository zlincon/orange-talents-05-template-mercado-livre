package br.com.zupacademy.lincon.mercadolivre.fechamentocompra;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraDTO {
    @Positive
    private int quantidade;
    @NotNull
    private Long idProduto;
    @NotNull
    private FormaPagamento formaPagamento;

    public int getQuantidade() {
        return quantidade;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }
}

package br.com.zupacademy.lincon.mercadolivre.fechamentocompra;

import javax.validation.constraints.NotNull;

public class RankingNovaCompraDTO {
    @NotNull
    private Long idCompra;
    @NotNull
    private Long idDonoProduto;

    public RankingNovaCompraDTO(Long idCompra, Long idDonoProduto) {
        this.idCompra = idCompra;
        this.idDonoProduto = idDonoProduto;
    }

    @Override
    public String toString() {
        return "RankingNovaCompraDTO{" +
                "idCompra=" + idCompra +
                ", idDonoProduto=" + idDonoProduto +
                '}';
    }
}

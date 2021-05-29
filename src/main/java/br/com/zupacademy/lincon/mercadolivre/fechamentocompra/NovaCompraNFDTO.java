package br.com.zupacademy.lincon.mercadolivre.fechamentocompra;

import javax.validation.constraints.NotNull;

public class NovaCompraNFDTO {
    @NotNull
    private Long idCompra;
    @NotNull
    private Long idComprador;

    public NovaCompraNFDTO(Long idCompra, Long idComprador) {
        this.idCompra = idCompra;
        this.idComprador = idComprador;
    }
}

package br.com.zupacademy.lincon.mercadolivre.fechamentocompra;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class RetornoPaypalRequest implements RetornoFormaPagamento {

    @Min(0)
    @Max(1)
    private int status;
    @NotBlank
    private String idTransacao;

    public RetornoPaypalRequest(int status, String idTransacao) {
        this.status = status;
        this.idTransacao = idTransacao;
    }

    @Override
    public Transacao toTransacao(Compra compra) {
        StatusTransacao statusTransacao = this.status == 0 ? StatusTransacao.ERRO : StatusTransacao.SUCESSO;

        return new Transacao(statusTransacao, idTransacao, compra);
    }
}

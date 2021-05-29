package br.com.zupacademy.lincon.mercadolivre.fechamentocompra;

public interface RetornoFormaPagamento {
    Transacao toTransacao(Compra compra);
}

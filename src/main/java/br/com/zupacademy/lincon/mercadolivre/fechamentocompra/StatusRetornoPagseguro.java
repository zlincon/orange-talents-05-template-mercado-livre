package br.com.zupacademy.lincon.mercadolivre.fechamentocompra;

public enum StatusRetornoPagseguro {
    SUCESSO, ERRO;

    public StatusTransacao normaliza() {
        if (this.equals(SUCESSO)) {
            return StatusTransacao.SUCESSO;
        }

        return StatusTransacao.ERRO;
    }
}

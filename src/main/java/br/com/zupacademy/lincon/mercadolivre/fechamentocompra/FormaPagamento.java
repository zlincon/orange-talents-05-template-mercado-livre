package br.com.zupacademy.lincon.mercadolivre.fechamentocompra;

import org.springframework.web.util.UriComponentsBuilder;

public enum FormaPagamento {
    paypal {
        String criaUrlRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder){
            String uriRetornoPaypal = uriComponentsBuilder.path("/retorno-paypal/{id}")
                    .buildAndExpand(compra.getId())
                    .toString();

            return "paypal.com?buyerId="+compra.getId()+"&redirectUrl="+uriRetornoPaypal;
        }
    },
    pagseguro {
        String criaUrlRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder){
            String uriRetornoPaypal = uriComponentsBuilder.path("/retorno" +
                    "-pagseguro" +
                    "/{id}")
                    .buildAndExpand(compra.getId())
                    .toString();

            return "pagseguro.com?returnId="+compra.getId()+"&redirectUrl="+uriRetornoPaypal;
        }
    };

    abstract String criaUrlRetorno(Compra compra,
                                   UriComponentsBuilder uriComponentsBuilder);
}

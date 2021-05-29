package br.com.zupacademy.lincon.mercadolivre.fechamentocompra;

import br.com.zupacademy.lincon.mercadolivre.cadastropergunta.Emails;
import br.com.zupacademy.lincon.mercadolivre.exceptionhandlers.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EventsNovaCompra {
    @Autowired
    private Set<EventoCompraSucesso> eventosCompraSucesso;
    @Autowired
    private Emails emails;

    public void processa(Compra compra) {
        if (compra.processadaComSucesso()) {
            eventosCompraSucesso.forEach(event -> event.processa(compra));
            emails.sendCompraProcessada(compra);
        } else {
            emails.sendCompraNaoProcessada(compra);
            throw new NegocioException("Erro ao processar evento da compra");
        }
    }
}

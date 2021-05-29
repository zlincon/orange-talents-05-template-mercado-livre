package br.com.zupacademy.lincon.mercadolivre.fechamentocompra;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NotaFiscal implements EventoCompraSucesso {
    @Override
    public void processa(Compra compra) {
        Assert.isTrue(compra.processadaComSucesso(), "Compra não conluida com sucesso: " + compra);

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(), "idComprador", compra.getComprador().getId());

        restTemplate.postForEntity("http://localhost:8080/notas-fiscais", request, String.class);
    }
}

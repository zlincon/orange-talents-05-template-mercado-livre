package br.com.zupacademy.lincon.mercadolivre.fechamentocompra;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SistemasInternosController {

    @PostMapping("/notas-fiscais")
    public void criaNota(@Valid @RequestBody NovaCompraNFDTO request) throws InterruptedException {
        System.out.println("Criando nota: " + request);
        Thread.sleep(150);
    }

    @PostMapping("/ranking")
    public void ranking(@Valid @RequestBody RankingNovaCompraDTO request) throws InterruptedException {
        System.out.println("Criando ranking: " + request);
        Thread.sleep(150);
    }

}

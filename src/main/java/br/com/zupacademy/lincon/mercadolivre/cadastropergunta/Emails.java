package br.com.zupacademy.lincon.mercadolivre.cadastropergunta;

import br.com.zupacademy.lincon.mercadolivre.fechamentocompra.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Emails {

    public void send(Pergunta pergunta){
        System.out.println("======================================");
        System.out.println("EMAIL");
        System.out.println("Subject: Nova pergunta...");
        System.out.println("NameFrom: " + pergunta.getUsuario().getEmail());
        System.out.println("From: novapergunta@ml.com");
        System.out.println("To: " + pergunta.getDonoProduto().getEmail());
        System.out.println("======================================");
    }

    public void sendNovaCompra(Compra compra) {
        System.out.println("======================================");
        System.out.println("EMAIL");
        System.out.println("Subject: Nova compra...");
        System.out.println("NameFrom: " + compra.getComprador().getEmail());
        System.out.println("From: compras@ml.com");
        System.out.println("To: " + compra.getProduto().getDono().getEmail());
        System.out.println("======================================");
    }

    public void sendCompraProcessada(Compra compra) {
        System.out.println("======================================");
        System.out.println("EMAIL");
        System.out.println("Subject: Compra processada...");
        System.out.println("NameFrom: processa@pagamentos.com");
        System.out.println("From: compras@ml.com");
        System.out.println("To: " + compra.getProduto().getDono().getEmail() + ", " +  compra.getComprador().getEmail());
        System.out.println("======================================");
    }

    public void sendCompraNaoProcessada(Compra compra) {
        System.out.println("======================================");
        System.out.println("EMAIL");
        System.out.println("Subject: Compra não processada...");
        System.out.println("NameFrom: processa@pagamentos.com");
        System.out.println("From: compras@ml.com");
        System.out.println("To: " + compra.getProduto().getDono().getEmail() + ", " +  compra.getComprador().getEmail());
        System.out.println("======================================");
    }
}

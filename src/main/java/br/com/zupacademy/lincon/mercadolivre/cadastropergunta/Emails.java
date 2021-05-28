package br.com.zupacademy.lincon.mercadolivre.cadastropergunta;

import br.com.zupacademy.lincon.mercadolivre.fechamentocompra.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Emails {

    public void send(Pergunta pergunta){
        System.out.println("======================================");
        System.out.println("<html>...</html>");
        System.out.println("Subject: Nova pergunta...");
        System.out.println("NameFrom: " + pergunta.getUsuario().getEmail());
        System.out.println("From: novapergunta@ml.com");
        System.out.println("To: " + pergunta.getDonoProduto().getEmail());
        System.out.println("======================================");
    }

    public void sendNovaCompra(Compra compra) {
        System.out.println("======================================");
        System.out.println("<body>...</body>");
        System.out.println("Subject: Nova compra...");
        System.out.println("NameFrom: " + compra.getComprador().getEmail());
        System.out.println("From: comptras@ml.com");
        System.out.println("To: " + compra.getProduto().getDono().getEmail());
        System.out.println("======================================");
    }
}

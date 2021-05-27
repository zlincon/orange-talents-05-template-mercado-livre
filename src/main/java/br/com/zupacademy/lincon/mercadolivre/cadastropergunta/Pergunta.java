package br.com.zupacademy.lincon.mercadolivre.cadastropergunta;

import br.com.zupacademy.lincon.mercadolivre.cadastroproduto.Produto;
import br.com.zupacademy.lincon.mercadolivre.cadastrousuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @NotNull
    @ManyToOne
    private Usuario usuario;
    @NotNull
    @ManyToOne
    private Produto produto;
    private OffsetDateTime timestamp;

    public Pergunta(String titulo, Usuario usuario, Produto produto) {
        this.titulo = titulo;
        this.usuario = usuario;
        this.produto = produto;
        this.timestamp = OffsetDateTime.now();
    }

    public PerguntaOutputDTO toOutputDTO() {
        return new PerguntaOutputDTO(titulo, usuario, produto, timestamp);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public  Usuario getDonoProduto() {
        return produto.getDono();
    }
}

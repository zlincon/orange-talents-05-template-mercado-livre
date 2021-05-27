package br.com.zupacademy.lincon.mercadolivre.cadastroopiniao;

import br.com.zupacademy.lincon.mercadolivre.cadastroproduto.Produto;
import br.com.zupacademy.lincon.mercadolivre.cadastrousuario.Usuario;

import javax.persistence.*;

@Entity
public class Opiniao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int nota;
    private String titulo;
    private String descricao;
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private Usuario consumidor;

    @Deprecated
    public Opiniao() {
    }

    public Opiniao(int nota, String titulo, String descricao, Produto produto, Usuario consumidor) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao= descricao;
        this.produto = produto;
        this.consumidor = consumidor;
    }

    public OpiniaoOutputDTO toOutputDTO() {
        return new OpiniaoOutputDTO(nota, titulo, descricao, produto,
                consumidor);
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getNota() {
        return nota;
    }
}

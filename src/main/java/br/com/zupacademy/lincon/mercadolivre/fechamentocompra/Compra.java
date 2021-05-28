package br.com.zupacademy.lincon.mercadolivre.fechamentocompra;

import br.com.zupacademy.lincon.mercadolivre.cadastroproduto.Produto;
import br.com.zupacademy.lincon.mercadolivre.cadastrousuario.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @Valid @ManyToOne
    private Produto produto;
    @Positive
    private int quantidade;
    @NotNull @Valid
    private Usuario comprador;
    @NotNull @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    public Compra(Produto produto, int quantidade, Usuario comprador, FormaPagamento formaPagamento) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.comprador = comprador;
        this.formaPagamento = formaPagamento;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public Long getId() {
        return id;
    }

    public String urlRedirecionamento(UriComponentsBuilder uriComponentsBuilder) {
        return this.formaPagamento.criaUrlRetorno(this, uriComponentsBuilder);
    }
}

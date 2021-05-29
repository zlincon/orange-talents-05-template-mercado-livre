package br.com.zupacademy.lincon.mercadolivre.fechamentocompra;

import br.com.zupacademy.lincon.mercadolivre.cadastroproduto.Produto;
import br.com.zupacademy.lincon.mercadolivre.cadastrousuario.Usuario;
import br.com.zupacademy.lincon.mercadolivre.exceptionhandlers.NegocioException;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Valid
    @ManyToOne
    private Produto produto;
    @Positive
    private int quantidade;
    @NotNull
    @Valid
    @ManyToOne
    private Usuario comprador;
    @NotNull
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;
    @Enumerated(EnumType.STRING)
    private StatusCompra statusCompra;
    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();

    @Deprecated
    public Compra() {
    }

    public Compra(Produto produto, int quantidade, Usuario comprador, FormaPagamento formaPagamento) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.comprador = comprador;
        this.formaPagamento = formaPagamento;
        this.statusCompra = StatusCompra.INICIADA;
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

    public void adicionaTransacao(@Valid RetornoFormaPagamento retornoFormaPagamento) {
        Transacao novaTransacao = retornoFormaPagamento.toTransacao(this);
        if(this.transacoes.contains(novaTransacao)){
            throw new NegocioException("Já existe uma transacao igual a essa processada "+novaTransacao);
        }
        Assert.isTrue(!this.transacoes.contains(novaTransacao),
                "Já existe uma transacao igual a essa processada "
                        + novaTransacao);
        if(!transacoesConcluidasComSucesso().isEmpty()){
            throw new NegocioException("Esta compra já foi concluída com sucesso");
        }
        Assert.isTrue(transacoesConcluidasComSucesso().isEmpty(), "Essa compra já foi concluída com sucesso");
        this.transacoes.add(novaTransacao);
        this.statusCompra = StatusCompra.CONCLUIDA;
    }

    public boolean processadaComSucesso() {
        return !transacoesConcluidasComSucesso().isEmpty();
    }

    private Set<Transacao> transacoesConcluidasComSucesso() {
        Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream()
                .filter(Transacao::concluidaComSucesso).collect(Collectors.toSet());

        Assert.isTrue(transacoesConcluidasComSucesso.size() <= 1, "Ops! Tem mais de uma transação concluída com sucesso para esta compra." + this.id);

        return transacoesConcluidasComSucesso;

    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", produto=" + produto +
                ", quantidade=" + quantidade +
                ", comprador=" + comprador +
                ", formaPagamento=" + formaPagamento +
                ", statusCompra=" + statusCompra +
                ", transacoes=" + transacoes +
                '}';
    }
}

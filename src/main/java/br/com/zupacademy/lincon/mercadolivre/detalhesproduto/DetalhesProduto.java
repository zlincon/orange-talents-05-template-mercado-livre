package br.com.zupacademy.lincon.mercadolivre.detalhesproduto;

import br.com.zupacademy.lincon.mercadolivre.cadastroopiniao.Opiniao;
import br.com.zupacademy.lincon.mercadolivre.cadastroproduto.CaracteristicaProdutoDTO;
import br.com.zupacademy.lincon.mercadolivre.cadastroproduto.Produto;
import br.com.zupacademy.lincon.mercadolivre.cadastroproduto.ProdutoOutputDTO;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class DetalhesProduto {
    private final Set<Map<String, String>> opinioes;
    private final long totalOpinioes;
    private final double mediaNotas;
    private final SortedSet<String> perguntas;
    private String nome;
    private BigDecimal valor;
    private Integer quantidade;
    private Set<CaracteristicaProdutoDTO> caracteristicas;
    private String descricao;
    private String categoria;
    private String dono;
    private OffsetDateTime timestamp;
    private Set<String> linksImagens;

    public DetalhesProduto(Produto produto) {
        ProdutoOutputDTO produtoOutputDTO = produto.toOutoutDTO();
        this.nome = produtoOutputDTO.getNome();
        this.valor = produtoOutputDTO.getValor();
        this.quantidade = produtoOutputDTO.getQuantidade();
        this.caracteristicas = produtoOutputDTO.getCaracteristicas().stream().map(CaracteristicaProdutoDTO::new).collect(Collectors.toSet());
        this.descricao = produtoOutputDTO.getDescricao();
        this.categoria = produtoOutputDTO.getCategoria();
        this.dono = produtoOutputDTO.getDono();
        this.timestamp = produtoOutputDTO.getTimestamp();
        this.linksImagens = produtoOutputDTO.getImagens().stream().map(imagem -> new String(imagem.getLink())).collect(Collectors.toSet());

        List<Opiniao> opinioes = produto.getOpinioes();

        this.opinioes = opinioes.stream().map(opiniao -> {
            return Map.of("titulo", opiniao.getTitulo(), "descricao", opiniao.getDescricao());
        }).collect(Collectors.toSet());

        OptionalDouble possivelMedia = opinioes.stream().map(opiniao -> opiniao.getNota()).mapToInt(nota -> nota).average();

        this.mediaNotas = possivelMedia.orElse(0.0);

        this.perguntas = produto.getPerguntas().stream().map(pergunta -> pergunta.getTitulo()).collect(Collectors.toCollection(TreeSet::new));

        this.totalOpinioes = opinioes.stream().count();

    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDono() {
        return dono;
    }

    public Set<CaracteristicaProdutoDTO> getCaracteristicas() {
        return caracteristicas;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public Set<Map<String, String>> getOpinioes() {
        return opinioes;
    }

    public long getTotalOpinioes() {
        return totalOpinioes;
    }

    public double getMediaNotas() {
        return mediaNotas;
    }

    public Set<String> getPerguntas() {
        return perguntas;
    }

    public Set<String> getLinksImagens() {
        return linksImagens;
    }
}

package br.com.zupacademy.lincon.mercadolivre.cadastroproduto;

import br.com.zupacademy.lincon.mercadolivre.cadastrocategoria.Categoria;
import br.com.zupacademy.lincon.mercadolivre.cadastroopiniao.Opiniao;
import br.com.zupacademy.lincon.mercadolivre.cadastropergunta.Pergunta;
import br.com.zupacademy.lincon.mercadolivre.cadastrousuario.Usuario;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer quantidade;
    private String descricao;
    private BigDecimal valor;
    @NotNull
    @ManyToOne
    private Categoria categoria;
    @ManyToOne
    private Usuario dono;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();
    private OffsetDateTime timestamp = OffsetDateTime.now();
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens = new HashSet<>();
    @OneToMany(mappedBy = "produto")
    @OrderBy("titulo asc")
    private SortedSet<Pergunta> perguntas = new TreeSet<>();
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private List<Opiniao> opinioes = new ArrayList<>();

    @Deprecated
    public Produto() {
    }

    public Produto(String nome, Integer quantidade,
                   String descricao, BigDecimal valor, Categoria categoria,
                   Usuario dono, List<CaracteristicaProdutoDTO> caracteristicas) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        this.dono = dono;
        this.caracteristicas.addAll(caracteristicas.stream()
                .map(caracteristica -> caracteristica.toModel(this))
                .collect(Collectors.toSet()));
        Assert.isTrue(this.caracteristicas.size() >= 3, "Todo produto precisa" +
                " ter no mínimo 3 características");
    }

    public ProdutoOutputDTO toOutoutDTO() {
        return new ProdutoOutputDTO(id, nome, quantidade, descricao,
                valor, categoria, dono,
                caracteristicas, timestamp, imagens);
    }

    public void associaImagens(Set<String> links) {
        Set<ImagemProduto> imagens = links.stream()
                .map(link -> new ImagemProduto(this, link))
                .collect(Collectors.toSet());

        this.imagens.addAll(imagens);
    }

    public void setImagens(Set<ImagemProduto> imagens) {
        this.imagens = imagens;
    }

    public Long getId() {
        return id;
    }

    public Usuario getDono() {
        return dono;
    }

    public boolean pertendeAoUsuario(Usuario dono) {
        return this.dono.equals(dono);
    }

    public List<Opiniao> getOpinioes() {
        return opinioes;
    }

    public SortedSet<Pergunta> getPerguntas() {
        return perguntas;
    }
}

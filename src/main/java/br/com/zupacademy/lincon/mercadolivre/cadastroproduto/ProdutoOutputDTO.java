package br.com.zupacademy.lincon.mercadolivre.cadastroproduto;

import br.com.zupacademy.lincon.mercadolivre.cadastrocategoria.Categoria;
import br.com.zupacademy.lincon.mercadolivre.cadastrousuario.Usuario;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

public class ProdutoOutputDTO {
    private Long id;
    private String nome;
    private BigDecimal valor;
    private Integer quantidade;
    private Set<CaracteristicaProduto> caracteristicas;
    private String descricao;
    private String categoria;
    private String dono;
    private OffsetDateTime timestamp;
    private Set<ImagemProduto> imagens;

    public ProdutoOutputDTO(Long id, String nome, Integer quantidade,
                            String descricao, BigDecimal valor, Categoria categoria,
                            Usuario dono,
                            Set<CaracteristicaProduto> caracteristicas,
                            OffsetDateTime timestamp,
                            Set<ImagemProduto> imagens) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoria = categoria.getNome();
        this.dono = dono.getEmail();
        this.timestamp = timestamp;
        this.imagens = imagens;
    }

    public Long getId() {
        return id;
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

    public Set<CaracteristicaProduto> getCaracteristicas() {
        return caracteristicas;
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

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public Set<ImagemProduto> getImagens() {
        return imagens;
    }
}

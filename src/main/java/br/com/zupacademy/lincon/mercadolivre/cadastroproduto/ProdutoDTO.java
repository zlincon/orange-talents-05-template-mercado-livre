package br.com.zupacademy.lincon.mercadolivre.cadastroproduto;

import br.com.zupacademy.lincon.mercadolivre.cadastrocategoria.Categoria;
import br.com.zupacademy.lincon.mercadolivre.cadastrousuario.Usuario;
import br.com.zupacademy.lincon.mercadolivre.exceptionhandlers.NegocioException;
import br.com.zupacademy.lincon.mercadolivre.utils.UniqueValue;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProdutoDTO {
    @NotBlank
    @UniqueValue(domainClass = Produto.class, fieldName = "nome")
    private String nome;
    @Positive
    @NotNull
    private BigDecimal valor;
    @Positive
    @NotNull
    private Integer quantidade;
    @Size(min = 3)
    @Valid
    private List<CaracteristicaProdutoDTO> caracteristicas =
            new ArrayList<>();
    @NotBlank
    @Size(max = 1000)
    private String descricao;
    @NotNull
    private Long idCategoria;

    public ProdutoDTO(String nome, BigDecimal valor, Integer quantidade,
                      String descricao, Long idCategoria) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
    }

    public List<CaracteristicaProdutoDTO> getCaracteristicas() {
        return caracteristicas;
    }

    public Produto toModel(EntityManager manager, Usuario dono) {
        Categoria categoria = manager.find(Categoria.class, idCategoria);

        if (categoria == null) {
            throw new NegocioException("Categoria informada não existe");
        }

        return new Produto(nome, quantidade, descricao, valor, categoria,
                dono, caracteristicas);
    }

    public Set<String> buscaCaracteristicasIguais() {
        HashSet<String> nomesIguais = new HashSet<>();
        HashSet<String> resultados = new HashSet<>();

        for (CaracteristicaProdutoDTO caracteristica : caracteristicas) {
            String nome = caracteristica.getNome();

            if (!nomesIguais.add(nome)) {
                resultados.add(nome);
            }
        }

        return resultados;
    }
}

package br.com.zupacademy.lincon.mercadolivre.cadastroproduto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class CaracteristicaProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
    @ManyToOne
    private Produto produto;

    public CaracteristicaProduto(String nome, String descricao, Produto produto) {
        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}

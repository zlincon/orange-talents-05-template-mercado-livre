package br.com.zupacademy.lincon.mercadolivre.cadastroproduto;

import javax.validation.constraints.NotBlank;

public class CaracteristicaProdutoDTO {
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;

    public CaracteristicaProdutoDTO(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public CaracteristicaProduto toModel(Produto produto){
        return new CaracteristicaProduto(nome, descricao, produto);
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}

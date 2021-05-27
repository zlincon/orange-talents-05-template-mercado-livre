package br.com.zupacademy.lincon.mercadolivre.cadastroopiniao;

import br.com.zupacademy.lincon.mercadolivre.cadastroproduto.Produto;
import br.com.zupacademy.lincon.mercadolivre.cadastrousuario.Usuario;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class OpiniaoOutputDTO {
    @Min(1) @Max(5)
    private int nota;
    @NotBlank
    private String titulo;
    @NotBlank
    @Size(max = 500)
    private String descricao;
    private Long idProduto;
    private Long idConsumidor;

    public OpiniaoOutputDTO(int nota, String titulo, String descricao,
                            Produto produto, Usuario consumidor) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.idProduto = produto.getId();
        this.idConsumidor = consumidor.getId();
    }

    public int getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public Long getIdConsumidor() {
        return idConsumidor;
    }
}

package br.com.zupacademy.lincon.mercadolivre.cadastropergunta;

import br.com.zupacademy.lincon.mercadolivre.cadastroproduto.Produto;
import br.com.zupacademy.lincon.mercadolivre.cadastroproduto.ProdutoOutputDTO;
import br.com.zupacademy.lincon.mercadolivre.cadastrousuario.Usuario;

import java.time.OffsetDateTime;

public class PerguntaOutputDTO {
    private String titulo;
    private String usuario;
    private Long idProduto;
    private OffsetDateTime timestamp;

    public PerguntaOutputDTO(String titulo, Usuario usuario,
                             Produto produto, OffsetDateTime timestamp) {
        this.titulo = titulo;
        this.usuario = usuario.getEmail();
        this.idProduto = produto.getId();
        this.timestamp = timestamp;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getUsuario() {
        return usuario;
    }

    public Long getProduto() {
        return idProduto;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }
}

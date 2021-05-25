package br.com.zupacademy.lincon.mercadolivre.cadastrocategoria;

import br.com.zupacademy.lincon.mercadolivre.exceptionhandlers.NegocioException;
import br.com.zupacademy.lincon.mercadolivre.utils.UniqueValue;
import org.springframework.util.Assert;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class CategoriaDTO {
    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;
    @Positive
    private Long idCategoriaMae;

    public CategoriaDTO(String nome, Long idCategoriaMae) {
        this.nome = nome;
        this.idCategoriaMae = idCategoriaMae;
    }

    @Override
    public String toString() {
        return "CategoriaDTO{nome='" + nome + '\'' + ", idCategoriaMae=" + idCategoriaMae + '}';
    }

    public Categoria toModel(EntityManager manager) {
        if (idCategoriaMae != null) {
            Categoria categoriaMae = manager.find(Categoria.class,
                    idCategoriaMae);
            if(categoriaMae == null) {
                throw new NegocioException("O id da categoriaMae precisa ser válido.");
            }
            Assert.notNull(categoriaMae, "O id da categoriaMae precisa ser válido.");
            return new Categoria(nome, categoriaMae);
        }
        return new Categoria(nome);
    }
}

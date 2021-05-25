package br.com.zupacademy.lincon.mercadolivre;

import br.com.zupacademy.lincon.mercadolivre.cadastrocategoria.Categoria;
import br.com.zupacademy.lincon.mercadolivre.cadastrocategoria.CategoriaDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.persistence.EntityManager;

public class NovaCategoriaRequestTest {
    @Test
    @DisplayName("deveria cadastrar categoria sem mae")
    void teste1() throws Exception {
        CategoriaDTO request = new CategoriaDTO("nome", null);

        EntityManager manager = Mockito.mock(EntityManager.class);
        request.toModel(manager);

        Mockito.verify(manager, Mockito.never())
                .find(Mockito.eq(Categoria.class), Mockito.anyLong());
    }

    @Test
    @DisplayName("Deveria cadastrar categoria com mae")
    void teste2() {
        CategoriaDTO request = new CategoriaDTO("nome", 11L);

        EntityManager manager = Mockito.mock(EntityManager.class);
        Categoria categoriaMae = new Categoria("teste");

        Mockito.when(manager.find(Categoria.class, 11L)).thenReturn(categoriaMae);
        request.toModel(manager);

        Mockito.verify(manager).find(Categoria.class, 11L);
    }
}

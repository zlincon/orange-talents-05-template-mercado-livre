package br.com.zupacademy.lincon.mercadolivre.detalhesproduto;

import br.com.zupacademy.lincon.mercadolivre.cadastroproduto.Produto;
import br.com.zupacademy.lincon.mercadolivre.exceptionhandlers.NegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
public class DetalheProdutoController {
    @PersistenceContext
    private EntityManager manager;

    @GetMapping("/api/produtos/{id}")
    public ResponseEntity<DetalhesProduto> getDetalhesProduto(@PathVariable("id") Long id) {
        Produto produto = manager.find(Produto.class, id);

        if(produto == null) {
            throw new NegocioException("Produto informado não existe", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(new DetalhesProduto(produto));
    }
}

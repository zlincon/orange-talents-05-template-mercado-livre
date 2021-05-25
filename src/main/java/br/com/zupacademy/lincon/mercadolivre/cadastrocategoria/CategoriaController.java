package br.com.zupacademy.lincon.mercadolivre.cadastrocategoria;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    @PersistenceContext
    EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity save(@RequestBody @Valid CategoriaDTO categoriaDTO) {
        Categoria categoria = categoriaDTO.toModel(manager);
        manager.persist(categoria);
        return ResponseEntity.ok().build();
    }
}

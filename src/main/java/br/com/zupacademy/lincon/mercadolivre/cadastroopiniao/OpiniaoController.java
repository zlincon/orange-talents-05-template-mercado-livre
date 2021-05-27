package br.com.zupacademy.lincon.mercadolivre.cadastroopiniao;

import br.com.zupacademy.lincon.mercadolivre.cadastroproduto.Produto;
import br.com.zupacademy.lincon.mercadolivre.cadastrousuario.Usuario;
import br.com.zupacademy.lincon.mercadolivre.cadastrousuario.UsuarioRepository;
import br.com.zupacademy.lincon.mercadolivre.exceptionhandlers.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
public class OpiniaoController {

    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/api/produtos/{id}/opinioes")
    @Transactional
    public ResponseEntity<OpiniaoOutputDTO> adiciona(@RequestBody @Valid OpiniaoDTO opiniao,
                                  @PathVariable("id") Long id){
        Produto produto = manager.find(Produto.class, id);

        if(produto == null) {
            throw new NegocioException("Produto selecionado não existe");
        }

        Usuario consumidor = usuarioRepository.findByEmail("adm@adm.com")
                .orElseThrow(() -> new NegocioException("Usuario não encontrado"));

        Opiniao novaOpiniao = opiniao.toModel(produto, consumidor);

        manager.persist(novaOpiniao);

        OpiniaoOutputDTO opiniaoOutputDTO = novaOpiniao.toOutputDTO();

        return ResponseEntity.ok(opiniaoOutputDTO);
    }
}

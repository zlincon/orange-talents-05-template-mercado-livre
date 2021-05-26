package br.com.zupacademy.lincon.mercadolivre.cadastroproduto;

import br.com.zupacademy.lincon.mercadolivre.cadastrousuario.Usuario;
import br.com.zupacademy.lincon.mercadolivre.cadastrousuario.UsuarioRepository;
import br.com.zupacademy.lincon.mercadolivre.exceptionhandlers.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @PersistenceContext
    EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @InitBinder
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new ProibeNomesDuplicadosCaracteristicaValidador());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoOutputDTO> cria(@RequestBody @Valid ProdutoDTO produtoDTO){
        Usuario dono = usuarioRepository.findByEmail("adm@adm.com")
                .orElseThrow(() -> new NegocioException("Usuário não encontrado"));

        Produto produto = produtoDTO.toModel(manager, dono);
        manager.persist(produto);

        ProdutoOutputDTO produtoOutputDTO = produto.toOutoutDTO();

        return ResponseEntity.ok(produtoOutputDTO);
    }
}

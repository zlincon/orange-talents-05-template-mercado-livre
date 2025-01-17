package br.com.zupacademy.lincon.mercadolivre.cadastroproduto;

import br.com.zupacademy.lincon.mercadolivre.cadastrousuario.Usuario;
import br.com.zupacademy.lincon.mercadolivre.cadastrousuario.UsuarioRepository;
import br.com.zupacademy.lincon.mercadolivre.exceptionhandlers.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api/produtos")
@Validated
public class ProdutoController {

    @PersistenceContext
    EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private Uploader uploaderFake;

    @InitBinder(value = "ProdutoDRO")
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new ProibeNomesDuplicadosCaracteristicaValidador());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoOutputDTO> cria(@RequestBody @Valid ProdutoDTO produtoDTO) {
        Usuario dono = usuarioRepository.findByEmail("adm@adm.com")
                .orElseThrow(() -> new NegocioException("Usuário não encontrado"));

        Produto produto = produtoDTO.toModel(manager, dono);
        manager.persist(produto);

        ProdutoOutputDTO produtoOutputDTO = produto.toOutoutDTO();

        return ResponseEntity.ok(produtoOutputDTO);
    }

    @PostMapping("/{id}/imagens")
    @Transactional
    public ResponseEntity<ProdutoOutputDTO> adicionaImagens(@PathVariable("id") Long id,
                                                            @Valid ImagensDTO request) {
        Usuario dono =
                usuarioRepository.findByEmail("adm@adm.com").orElseThrow(() -> new NegocioException("Usuário não encontrado"));
        Set<String> links = uploaderFake.envia(request.getImagens());
        Produto produto = manager.find(Produto.class, id);

        if (produto == null) {
            throw new NegocioException("O produto informado não existe");
        }

        if (!produto.pertendeAoUsuario(dono)) {
            throw new NegocioException("Usuário " +
                    "não é dono deste produto", HttpStatus.FORBIDDEN);
        }


        produto.associaImagens(links);

        manager.merge(produto);

        return ResponseEntity.ok(produto.toOutoutDTO());
    }
}

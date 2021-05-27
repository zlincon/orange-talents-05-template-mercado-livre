package br.com.zupacademy.lincon.mercadolivre.cadastropergunta;

import br.com.zupacademy.lincon.mercadolivre.cadastroproduto.Produto;
import br.com.zupacademy.lincon.mercadolivre.cadastrousuario.Usuario;
import br.com.zupacademy.lincon.mercadolivre.cadastrousuario.UsuarioRepository;
import br.com.zupacademy.lincon.mercadolivre.exceptionhandlers.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
public class PerguntaController {

    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private Emails emails;

    @PostMapping("/api/produtos/{id}/perguntas")
    @Transactional
    public ResponseEntity<PerguntaOutputDTO> criar(@RequestBody @Valid PerguntaDTO perguntaDTO
            , @PathVariable("id") Long id){
        Produto produto = manager.find(Produto.class, id);

        if(produto == null){
            throw new NegocioException("Produto selecionado não existe");
        }

        Usuario usuario = usuarioRepository.findByEmail("b@b.com")
                .orElseThrow(() -> new NegocioException("Usuário não " +
                        "encontrado"));

        Pergunta pergunta = perguntaDTO.toModel(usuario, produto);

        manager.persist(pergunta);

        PerguntaOutputDTO perguntaOutputDTO = pergunta.toOutputDTO();

        emails.send(pergunta);

        return ResponseEntity.ok(perguntaOutputDTO);

    }
}

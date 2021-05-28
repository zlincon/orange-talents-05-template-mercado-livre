package br.com.zupacademy.lincon.mercadolivre.fechamentocompra;

import br.com.zupacademy.lincon.mercadolivre.cadastropergunta.Emails;
import br.com.zupacademy.lincon.mercadolivre.cadastroproduto.Produto;
import br.com.zupacademy.lincon.mercadolivre.cadastrousuario.Usuario;
import br.com.zupacademy.lincon.mercadolivre.cadastrousuario.UsuarioRepository;
import br.com.zupacademy.lincon.mercadolivre.exceptionhandlers.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
public class FechamentoCompraController {
    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private Emails emails;

    @PostMapping("/api/compras")
    @Transactional
    public ResponseEntity<String> compra(@RequestBody @Valid CompraDTO request,
                                 UriComponentsBuilder uriComponentsBuilder) throws BindException {
        Produto produto = manager.find(Produto.class, request.getIdProduto());

        int quantidade = request.getQuantidade();
        boolean abateu = produto.abateEstoque(quantidade);

        if(abateu) {
            Usuario comprador = usuarioRepository.findByEmail("b.b.com")
                    .orElseThrow(() -> new NegocioException("Usuario " +
                            "informado não encontrado"));

            FormaPagamento formaPagamento = request.getFormaPagamento();

            Compra compra = new Compra(produto, quantidade, comprador, formaPagamento);

            emails.sendNovaCompra(compra);

            return ResponseEntity.ok(compra.urlRedirecionamento(uriComponentsBuilder));
        }

        BindException problemaComEstoque = new BindException(request,
                "compraRequest");
        problemaComEstoque.reject(null, "Não foi possível realizar a compra " +
                "por conta do estoque");

        throw problemaComEstoque;
    }
}

package br.com.zupacademy.lincon.mercadolivre.fechamentocompra;

import br.com.zupacademy.lincon.mercadolivre.cadastropergunta.Emails;
import br.com.zupacademy.lincon.mercadolivre.cadastroproduto.Produto;
import br.com.zupacademy.lincon.mercadolivre.cadastrousuario.Usuario;
import br.com.zupacademy.lincon.mercadolivre.cadastrousuario.UsuarioRepository;
import br.com.zupacademy.lincon.mercadolivre.exceptionhandlers.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private EventsNovaCompra eventsNovaCompra;


    @PostMapping("/api/v1/compras")
    @Transactional
    public ResponseEntity<String> compra(@RequestBody @Valid CompraDTO request,
                                         UriComponentsBuilder uriComponentsBuilder) {
        Produto produto = manager.find(Produto.class, request.getIdProduto());

        if (produto == null) {
            throw new NegocioException("Produto não encontrado");
        }

        int quantidade = request.getQuantidade();
        boolean abateu = produto.abateEstoque(quantidade);

        if (abateu) {
            Usuario comprador = usuarioRepository.findByEmail("b@b.com")
                    .orElseThrow(() -> new NegocioException("Usuario " +
                            "informado não encontrado"));

            FormaPagamento formaPagamento = request.getFormaPagamento();

            Compra compra = new Compra(produto, quantidade, comprador, formaPagamento);
            manager.persist(compra);
            emails.sendNovaCompra(compra);

            return ResponseEntity.status(HttpStatus.FOUND).body(compra.urlRedirecionamento(uriComponentsBuilder));
        } else {
            throw new NegocioException("Não foi possível realizar a compra por conta do estoque");
        }

//        BindException problemaComEstoque = new BindException(request,
//                "compraRequest");
//        problemaComEstoque.reject(null, "Não foi possível realizar a compra " +
//                "por conta do estoque");
//
//        throw problemaComEstoque;
    }

    @PostMapping("/retorno-pagseguro/{id}")
    @Transactional
    public ResponseEntity<String> processamentoPagSeguro(@PathVariable("id") Long idCompra,
                                                         @RequestBody @Valid RetornoPagseguroRequest request) {
        return ResponseEntity.ok(processa(idCompra, request).toString());
    }

    @PostMapping("/retorno-paypal/{id}")
    @Transactional
    public ResponseEntity<String> processamentoPaypal(@PathVariable("id") Long idCompra,
                                                      @RequestBody @Valid RetornoPaypalRequest request) {
        return ResponseEntity.ok(processa(idCompra, request).toString());
    }

    private Compra processa(Long idCompra, RetornoFormaPagamento retornoFormaPagamento) {
        Compra compra = manager.find(Compra.class, idCompra);
        compra.adicionaTransacao(retornoFormaPagamento);
        manager.merge(compra);
        eventsNovaCompra.processa(compra);

        return compra;
    }
}

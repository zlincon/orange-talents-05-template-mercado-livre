package br.com.zupacademy.lincon.mercadolivre.fechamentocompra;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Entity
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusTransacao status;
    private String idTransacaoGateway;
    @NotNull
    private OffsetDateTime timestamp;
    @NotNull
    @Valid
    @ManyToOne
    private Compra compra;

    @Deprecated
    public Transacao() {
    }


    public Transacao(StatusTransacao statusTransacao, String idTransacao, Compra compra) {
        this.status = statusTransacao;
        this.compra = compra;
        this.idTransacaoGateway = idTransacao;
        this.timestamp = OffsetDateTime.now();
    }

    public boolean concluidaComSucesso() {
        return this.status.equals(StatusTransacao.SUCESSO);
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", status=" + status +
                ", idTransacaoGateway='" + idTransacaoGateway + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}

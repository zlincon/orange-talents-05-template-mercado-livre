package br.com.zupacademy.lincon.mercadolivre.cadastrousuario;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String senha;
    private Instant timestamp;

    @Deprecated
    public Usuario() {
    }

    public Usuario(String email, @Valid @NotNull SenhaLimpa senhaLimpa) {
        Assert.isTrue(StringUtils.hasLength(email), "email não pode ser em branco");
        Assert.notNull(senhaLimpa, "o objeto do tipo senha limpa não pode ser nulo");
        this.email = email;
        this.senha = senhaLimpa.hash();
        this.timestamp = Instant.now();
    }

    public UsuarioDTO toDTO() {
        return new UsuarioDTO(email, senha, timestamp);
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}

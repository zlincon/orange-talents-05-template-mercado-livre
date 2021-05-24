package br.com.zupacademy.lincon.mercadolivre.entities;

import br.com.zupacademy.lincon.mercadolivre.dtos.UsuarioDTO;
import br.com.zupacademy.lincon.mercadolivre.utils.SenhaLimpa;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    public Usuario(String email,@Valid @NotNull SenhaLimpa senhaLimpa) {
        Assert.isTrue(StringUtils.hasLength(email), "email não pode ser em branco");
        Assert.notNull(senhaLimpa, "o objeto do tipo senha limpa não pode ser nulo");
        this.email = email;
        this.senha = senhaLimpa.hash();
        this.timestamp = Instant.now();
    }


    public UsuarioDTO toDTO() {
        return new UsuarioDTO(email, senha, timestamp);
    }
}

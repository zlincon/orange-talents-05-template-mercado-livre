package br.com.zupacademy.lincon.mercadolivre.dtos;

import br.com.zupacademy.lincon.mercadolivre.entities.Usuario;
import br.com.zupacademy.lincon.mercadolivre.utils.SenhaLimpa;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Instant;

public class UsuarioDTO {
    @NotBlank
    @Email
    private String email;
    @Size(min = 6)
    @NotBlank
    private String senha;
    private Instant timestamp;

    @Deprecated
    public UsuarioDTO() {
    }

    public UsuarioDTO(@NotBlank @Email String email,@Size(min = 6) @NotBlank String senha, Instant timestamp) {
        this.email = email;
        this.senha = senha;
        this.timestamp = timestamp;
    }

    public Usuario toModel(){
        return new Usuario(email, new SenhaLimpa(senha));
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}

package br.com.zupacademy.lincon.mercadolivre.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

public class SenhaLimpa {
    private String senha;

    public SenhaLimpa(String senha) {
        Assert.hasLength(senha, "senha não pode ser em branco");
        Assert.isTrue(senha.length() >= 6, "senha tem que ter no mínimo 6 caracteres");
        this.senha = senha;
    }

    public String hash() {
        return new BCryptPasswordEncoder().encode(senha);
    }
}

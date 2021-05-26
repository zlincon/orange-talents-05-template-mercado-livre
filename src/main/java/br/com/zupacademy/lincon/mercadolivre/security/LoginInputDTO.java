package br.com.zupacademy.lincon.mercadolivre.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginInputDTO {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UsernamePasswordAuthenticationToken build() {
        return new UsernamePasswordAuthenticationToken(this.email,
                this.password);
    }
}

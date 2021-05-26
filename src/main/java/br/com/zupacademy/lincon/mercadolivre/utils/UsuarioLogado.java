package br.com.zupacademy.lincon.mercadolivre.utils;

import br.com.zupacademy.lincon.mercadolivre.cadastrousuario.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

public class UsuarioLogado implements UserDetails {

    private Usuario usuario;
    private User springUserDetails;

    public UsuarioLogado(@NotNull @Valid Usuario usuario) {
        this.usuario = usuario;
        springUserDetails = new User(usuario.getEmail(), usuario.getSenha(), List.of());
    }


    public Collection<GrantedAuthority> getAuthorities() {
        return springUserDetails.getAuthorities();
    }


    public String getPassword() {
        return springUserDetails.getPassword();
    }


    public String getUsername() {
        return springUserDetails.getUsername();
    }


    public boolean isEnabled() {
        return springUserDetails.isEnabled();
    }


    public boolean isAccountNonExpired() {
        return springUserDetails.isAccountNonExpired();
    }


    public boolean isAccountNonLocked() {
        return springUserDetails.isAccountNonLocked();
    }


    public boolean isCredentialsNonExpired() {
        return springUserDetails.isCredentialsNonExpired();
    }


    public Usuario get() {
        return usuario;
    }

}

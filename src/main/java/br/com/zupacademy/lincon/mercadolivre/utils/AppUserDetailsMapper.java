package br.com.zupacademy.lincon.mercadolivre.utils;

import br.com.zupacademy.lincon.mercadolivre.cadastrousuario.Usuario;
import br.com.zupacademy.lincon.mercadolivre.security.UserDetailsMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
public class AppUserDetailsMapper implements UserDetailsMapper {

    @Override
    public UserDetails map(Object shouldBeASystemUser) {
        return new UsuarioLogado((Usuario) shouldBeASystemUser);
    }

}

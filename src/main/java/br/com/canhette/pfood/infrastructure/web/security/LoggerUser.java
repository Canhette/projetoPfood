package br.com.canhette.pfood.infrastructure.web.security;

import br.com.canhette.pfood.domain.cliente.Cliente;
import br.com.canhette.pfood.domain.restaurante.Restaurante;
import br.com.canhette.pfood.domain.usuario.Usuario;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.List;

@Getter
public class LoggerUser implements UserDetails {


    private Usuario usuario;
    private Role role;

    //TODO entender isso
    private Collection<? extends GrantedAuthority > roles;

    public LoggerUser(Usuario usuario){
        this.usuario = usuario;

        Role role;

        if(usuario instanceof Cliente){
            role = Role.CLIENTE;
        } else if(usuario instanceof Restaurante){
            role = Role.RESTAURATE;
        } else{
            throw new IllegalStateException("Tipo de usuario invalido");
        }

        this.role = role;
        this.roles = List.of(new SimpleGrantedAuthority("Role_" + role));
        //TODO ver oq significa o SimpleGranted...



    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return usuario.getSenha();
    }

    @Override
    public String getUsername() {
        return usuario.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

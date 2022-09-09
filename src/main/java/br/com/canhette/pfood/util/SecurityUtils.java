package br.com.canhette.pfood.util;

import br.com.canhette.pfood.domain.cliente.Cliente;
import br.com.canhette.pfood.domain.restaurante.Restaurante;
import br.com.canhette.pfood.infrastructure.web.security.LoggerUser;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

public class SecurityUtils {

    public static LoggerUser loggerUser(){
        //recupera autenticanção do spring
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication instanceof AnonymousAuthenticationToken){
            return null;
        }

        return (LoggerUser) authentication.getPrincipal();
    }

    public static Cliente loggedCliente(){
        LoggerUser loggerUser = loggerUser();

        if(loggerUser == null){
            throw new IllegalStateException("Não existe um usuario logado");
        }

        if(!(loggerUser.getUsuario() instanceof Cliente)){
            throw new IllegalStateException("O usuário não é um cliente");
        }

        return (Cliente) loggerUser.getUsuario();
    }

    public static Restaurante loggedRestaurante(){
        LoggerUser loggerUser = loggerUser();

        if(loggerUser == null){
            throw new IllegalStateException("Não existe um usuario logado");
        }

        if(!(loggerUser.getUsuario() instanceof Restaurante)){
            throw new IllegalStateException("O usuário não é um cliente");
        }

        return (Restaurante) loggerUser.getUsuario();
    }
}

package br.com.canhette.pfood.infrastructure.web.security;

import br.com.canhette.pfood.domain.cliente.Cliente;
import br.com.canhette.pfood.domain.cliente.ClienteRepository;
import br.com.canhette.pfood.domain.restaurante.RestauranteRespository;
import br.com.canhette.pfood.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//TODO interder essa interface
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private RestauranteRespository restauranteRespository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = clienteRepository.findByEmail(username);

        if (usuario == null){
            usuario = restauranteRespository.findByEmail(username);

            if(usuario == null){
                throw new UsernameNotFoundException(username);
            }
        }

        return new LoggerUser(usuario);
    }
}

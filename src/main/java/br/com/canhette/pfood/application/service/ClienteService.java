package br.com.canhette.pfood.application.service;

import br.com.canhette.pfood.domain.cliente.Cliente;
import br.com.canhette.pfood.domain.cliente.ClienteRepository;
import br.com.canhette.pfood.domain.restaurante.Restaurante;
import br.com.canhette.pfood.domain.restaurante.RestauranteRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private RestauranteRespository restauranteRespository;

    @Transactional // transação no DB - caso uma transação falhe a jpa cuida para desfazer oque já foi feito
    public void saveCliente(Cliente cliente) throws ValidationException {

        if(!validateEmail(cliente.getEmail(), cliente.getId())){
        throw new ValidationException("O e-mail está duplicado");
        }

        if(cliente.getId() != null){
            Cliente clienteDB = clienteRepository.findById(cliente.getId()).orElseThrow();
            cliente.setSenha(clienteDB.getSenha());
        } else{
            cliente.encryptPassword();
        }

        clienteRepository.save(cliente);
    }

    private boolean validateEmail(String email, Integer id){

        Cliente clienteEmail = clienteRepository.findByEmail(email);
        Restaurante restauranteEmail = restauranteRespository.findByEmail(email);

        if(restauranteEmail != null) {
            return false;
        }

        if(clienteEmail != null){
            if(id == null){
                return false;
            }
            if(!clienteEmail.getId().equals(id)){
                return false;
            }
        }
        return true;
    }
}

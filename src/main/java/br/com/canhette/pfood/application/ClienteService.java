package br.com.canhette.pfood.application;

import br.com.canhette.pfood.domain.cliente.Cliente;
import br.com.canhette.pfood.domain.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public void saveCliente(Cliente cliente) throws ValidationException {

        if(!validateEmail(cliente.getEmail(), cliente.getId())){
        throw new ValidationException("O e-mail está duplicado");
        }
        clienteRepository.save(cliente);
    }

    private boolean validateEmail(String email, Integer id){
        Cliente clienteEmail = clienteRepository.findByEmail(email);

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

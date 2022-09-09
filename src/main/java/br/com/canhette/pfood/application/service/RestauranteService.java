package br.com.canhette.pfood.application.service;

import br.com.canhette.pfood.domain.cliente.Cliente;
import br.com.canhette.pfood.domain.cliente.ClienteRepository;
import br.com.canhette.pfood.domain.restaurante.Restaurante;
import br.com.canhette.pfood.domain.restaurante.RestauranteRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRespository restauranteRespository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ImageService imageService;

    @Transactional // transação no DB - caso uma transação falhe a jpa cuida para desfazer oque já foi feito
    public void saveRestaurante(Restaurante restaurante) throws ValidationException {

        if(!validateEmail(restaurante.getEmail(), restaurante.getId())){
            throw new ValidationException("O e-mail está duplicado");
        }

        if(restaurante.getId() != null){
            Restaurante restauranteDB = restauranteRespository.findById(restaurante.getId()).orElseThrow();
            restaurante.setSenha(restauranteDB.getSenha());
        } else{
            restaurante.encryptPassword();
            restauranteRespository.save(restaurante);
            restaurante.setLogotipoFileName();
            imageService.uploadLogoTipo(restaurante.getLogotipoFile(), restaurante.getLogotipo());
        }


    }

    private boolean validateEmail(String email, Integer id) {
        Restaurante restauranteEmail = restauranteRespository.findByEmail(email);
        Cliente clienteEmail = clienteRepository.findByEmail(email);

        if(clienteEmail != null) {
            return false;
        }

        if (restauranteEmail != null) {
            if (id == null) {
                return false;
            }
            if (!restauranteEmail.getId().equals(id)) {
                return false;
            }
        }

        return true;
    }
}

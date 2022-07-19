package br.com.canhette.pfood.application;

import br.com.canhette.pfood.domain.restaurante.Restaurante;
import br.com.canhette.pfood.domain.restaurante.RestauranteRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRespository restauranteRespository;

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
            //TODO: Upload
        }


    }

    private boolean validateEmail(String email, Integer id) {
        Restaurante restauranteEmail = restauranteRespository.findByEmail(email);

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

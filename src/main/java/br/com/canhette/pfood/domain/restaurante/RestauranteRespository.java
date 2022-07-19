package br.com.canhette.pfood.domain.restaurante;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRespository extends JpaRepository<Restaurante, Integer> {
    Restaurante findByEmail(String email);
}

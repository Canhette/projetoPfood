package br.com.canhette.pfood.application.test;

import br.com.canhette.pfood.domain.cliente.Cliente;
import br.com.canhette.pfood.domain.cliente.ClienteRepository;
import br.com.canhette.pfood.domain.restaurante.CategoriaRestaurante;
import br.com.canhette.pfood.domain.restaurante.CategoriaRestauranteRespository;
import br.com.canhette.pfood.domain.restaurante.Restaurante;
import br.com.canhette.pfood.domain.restaurante.RestauranteRespository;
import br.com.canhette.pfood.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class InsertDataForTesting {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private RestauranteRespository restauranteRespository;

    @Autowired
    private CategoriaRestauranteRespository categoriaRestauranteRespository;

    @EventListener //executado ao iniciar a aplicação
    private void onAplicationEvent(ContextRefreshedEvent event){
        clientes();
        restaurantes();
    }

    private void clientes() {

        Cliente c = new Cliente();
        c.setNome("João Silva");
        c.setEmail("joao@bluefood.com.br");
        c.setSenha(StringUtils.encrypt("c"));
        c.setCep("83212180");
        c.setCpf("08225013645");
        c.setTelefone("9935490565");
        clienteRepository.save(c);

        c = new Cliente();
        c.setNome("Maria Silva");
        c.setEmail("maria@bluefood.com.br");
        c.setSenha(StringUtils.encrypt("c"));
        c.setCep("83212180");
        c.setCpf("09827813645");
        c.setTelefone("9845485568");
        clienteRepository.save(c);
    }

    private void restaurantes(){

        CategoriaRestaurante pizza = categoriaRestauranteRespository.findById(1).orElseThrow();
        CategoriaRestaurante sanduiche = categoriaRestauranteRespository.findById(2).orElseThrow();
        CategoriaRestaurante sobremesa = categoriaRestauranteRespository.findById(4).orElseThrow();
        CategoriaRestaurante japones = categoriaRestauranteRespository.findById(5).orElseThrow();

        Restaurante r = new Restaurante();
        r.setNome("Bubger King");
        r.setEmail("r1@bluefood.com.br");
        r.setSenha(StringUtils.encrypt("r"));
        r.setCnpj("00000000000001");
        r.setTaxaEntrega(BigDecimal.valueOf(3.2));
        r.setTelefone("1235498765");
        r.getCategorias().add(sanduiche);
        r.getCategorias().add(sobremesa);
        r.setLogotipo("0001-logo.png");
        r.setTempoEntrega(30);
        restauranteRespository.save(r);

        r = new Restaurante();
        r.setNome("MC Naldo's");
        r.setEmail("r2@bluefood.com.br");
        r.setSenha(StringUtils.encrypt("r"));
        r.setCnpj("00000000000002");
        r.setTaxaEntrega(BigDecimal.valueOf(5.2));
        r.setTelefone("4569873546");
        r.getCategorias().add(pizza);
        r.getCategorias().add(japones);
        r.setLogotipo("0002-logo.png");
        r.setTempoEntrega(30);
        restauranteRespository.save(r);

    }
}

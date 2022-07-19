package br.com.canhette.pfood.domain.cliente;

import br.com.canhette.pfood.domain.usuario.Usuario;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true , callSuper = true)
@Entity
@Table(name = "cliente")
public class Cliente extends Usuario {

    @NotBlank(message = "O CPF não pode ser vazio")
    @Pattern(regexp = "[0-9]{11}", message = "O CPF possui o formato inválido")
    @Column(length = 11, nullable = false)
    private String cpf;

    @NotBlank(message = "O CP não pode ser vazio")
    @Pattern(regexp = "[0-9]{8}", message = "O CP possui o formato inválido")
    @Column(length = 8)
    private String cep;

}

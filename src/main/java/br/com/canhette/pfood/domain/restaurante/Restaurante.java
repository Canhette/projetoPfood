package br.com.canhette.pfood.domain.restaurante;

import br.com.canhette.pfood.domain.usuario.Usuario;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true , callSuper = true)
@Entity
@Table(name = "restaurante")
public class Restaurante extends Usuario {

    @NotBlank(message = "O CPF não pode ser vazio")
    @Pattern(regexp = "[0-9]{14}", message = "O CPF possui o formato inválido")
    @Column(length = 14, nullable = false)
    private String cnpj;

    @Size(max = 80)
    private String logotipo;

    private transient MultipartFile logotipoFile;

    @NotNull(message = "A taxa de entrega nao pode ser vazia")
    @Min(0)
    @Max(99)
    private BigDecimal taxaEntrega;

    @NotNull(message = "A taxa de entrega nao pode ser vazia")
    @Min(0)
    @Max(120)
    private Integer tempoEntrega;

    @ManyToMany
    @JoinTable(
            name ="restaurante_has_categoria",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_restaurante_id")
    )
    @Size(min = 1, message = "O restaurante precisa ter pelo menos uma categoria")
    @ToString.Exclude // boa pratica excluir do ToString relacionamentos. quando usar lombook
    private Set<CategoriaRestaurante> categorias = new HashSet<>(0); // não é legal deixar a coleção null

    public void setLogotipoFileName(){
        if(getId() == null){
            throw new IllegalStateException("É preciso primeiro gravar o registro");
        }

        //TODO: mudar forma de ler extensão
        this.logotipo = String.format("%04d-logo.%s", getId(), ".png");
    }
}

package es.Alfonso.tienda.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
public class Faccion {

    @Id
    private long id;
    @NotEmpty
    private String nombre;

    @OneToMany(mappedBy = "faccion", fetch = FetchType.EAGER)
    private List<Usuario> usuarios;
}


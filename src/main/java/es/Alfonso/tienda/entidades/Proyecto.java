package es.Alfonso.tienda.entidades;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
public class Proyecto {

    @Id
    @GeneratedValue
    private long id;
    @NotEmpty
    private String nombre;

    private String descripcion;

    @ManyToMany(mappedBy = "proyectos")
    private List<Usuario> usuario;
}

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
public class Genero {
    @Id
    private long id;
    @NotEmpty
    private String nombre;

    @OneToMany(mappedBy = "genero", fetch = FetchType.EAGER)
    private List<Juego> juegos;
}

package es.Alfonso.tienda.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Juego {
    @Id
    @GeneratedValue
    private long id;
    @NotEmpty
    private String nombre;
    private String imagen;
    @NotEmpty
    private String empresa;
    @NotEmpty
    private String precio;
    private boolean disponible;
    @ManyToOne
    private Genero genero;

}

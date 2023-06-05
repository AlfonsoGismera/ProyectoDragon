package es.Alfonso.tienda.entidades;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@EntityListeners(AuditingEntityListener.class)
public class Usuario {

    //  Antiguo
//  @Min(value=1, message="{usuario.id.mayorquecero}")
    @Id
    @GeneratedValue
//  @Positive(message="{usuario.id.mayorquecero}")
    private long id;
    @NotEmpty(message = "{usuario.nombre.vacio}")
    private String nombre;
    @NotEmpty(message = "{usuario.vacio}")
    @Email(message = "{usuario.formato}")
    private String email;
    @NotEmpty(message = "{usuario.vacio}")
    @Positive(message = "{usuario.id.mayorquecero}")
    private String telefono;
    @ManyToOne
    private Faccion faccion;

    private String imagen;

    @ManyToMany
    @JoinTable(name = "usuario_proyecto",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "proyecto_id")
    )
    private List<Proyecto> proyectos;
//    Login
    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    private String role;
}

package es.Alfonso.tienda.entidades;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
public class Preferencias {
    @Id
    private Long id;

    private String idioma;

    @OneToOne(cascade = CascadeType.MERGE)
    @MapsId
    private Usuario usuario;
}

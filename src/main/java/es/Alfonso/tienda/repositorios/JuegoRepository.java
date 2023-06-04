package es.Alfonso.tienda.repositorios;

import es.Alfonso.tienda.entidades.Juego;
import es.Alfonso.tienda.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface JuegoRepository extends JpaRepository<Juego, Long> {

    Juego findByNombre(String nombre);

    // Consultas derivadas del nombre del método
    List<Juego> findByNombreContainsIgnoreCase(String filtro);

    List<Juego> findByNombreStartingWithIgnoreCase(String filtro);

    // Consulta por JPQL
    @Query("select e from Juego e where lower(e.nombre)  like %?1%")
    List<Juego> encuentraPorNombreEmail(String cadena);

    // Consulta por SQL nativo
    @Query(value = "SELECT * FROM Juego WHERE LOWER(NOMBRE) LIKE CONCAT('%',?1,'%') OR LOWER(EMPRESA) LIKE CONCAT('%',?1,'%') OR LOWER(PRECIO) LIKE CONCAT('%',?1,'%')", nativeQuery = true)
    List<Juego> encuentraPorNombreEmailOTelefonoNativa(String cadena);
}
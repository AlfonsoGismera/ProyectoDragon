package es.Alfonso.tienda.repositorios;

import es.Alfonso.tienda.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByNombre(String nombre);

    // Consultas derivadas del nombre del método
    List<Usuario> findByNombreContainsIgnoreCase(String filtro);

    List<Usuario> findByNombreStartingWithIgnoreCase(String filtro);

    // Consulta por JPQL
    @Query("select e from Usuario e where lower(e.nombre) like %?1% or lower(e.email) like %?1% or lower(e.telefono) like %?1%")
    List<Usuario> encuentraPorNombreEmail(String cadena);

    // Consulta por SQL nativo
    @Query(value = "SELECT * FROM Usuario WHERE LOWER(NOMBRE) LIKE CONCAT('%',?1,'%') OR LOWER(EMAIL) LIKE CONCAT('%',?1,'%') OR LOWER(TELEFONO) LIKE CONCAT('%',?1,'%')", nativeQuery = true)
    List<Usuario> encuentraPorNombreEmailOTelefonoNativa(String cadena);
    //    Login
    Usuario findFirstByUsername(String username);
}

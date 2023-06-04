package es.Alfonso.tienda.repositorios;


import es.Alfonso.tienda.entidades.Preferencias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PostAuthorize;

import java.util.Optional;

public interface PreferenciaRepository extends JpaRepository<Preferencias, Long> {
    //@PreAuthorize("#entity.usuario.username == authentication.name")
    Preferencias save(Preferencias entity);

    @PostAuthorize("returnObject != null and returnObject.usuario.username == authentication.name")
    Optional<Preferencias> findById(Long id);

    @Query("select p from #{#entityName} p where p.usuario.username = ?#{authentication.name}")
    Optional<Preferencias> findUsuarioPreferencias();
}

package es.Alfonso.tienda.servicios;

import es.Alfonso.tienda.entidades.Preferencias;
import es.Alfonso.tienda.repositorios.PreferenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PreferenciasService {
    private final PreferenciaRepository repositorio;

    public Optional<Preferencias> findUsuarioPreferencias() {
        return repositorio.findUsuarioPreferencias();
    }

    public Preferencias save(Preferencias preferencias) {
        return repositorio.save(preferencias);
    }

    @PreAuthorize("#preferencias.usuario.username == authentication.name")
    public Preferencias saveAuth(Preferencias preferencias) {
        return repositorio.save(preferencias);
    }

    @Transactional
    public void deleteAll() {
        repositorio.deleteAllInBatch();
    }
}

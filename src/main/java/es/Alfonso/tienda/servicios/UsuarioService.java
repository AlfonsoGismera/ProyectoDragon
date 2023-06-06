package es.Alfonso.tienda.servicios;

import es.Alfonso.tienda.entidades.Usuario;
import es.Alfonso.tienda.repositorios.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository repositorio;
    private final PasswordEncoder passwordEncoder;

    public void add(Usuario e) {
        repositorio.save(e);
    }

    public List<Usuario> findAll() {
        return repositorio.findAll();
    }

    public Usuario findById(long id) {
        return repositorio.findById(id).orElse(null);
    }

    public List<Usuario> findByName(String filtro) {
        List<Usuario> result = repositorio.findByNombreContainsIgnoreCase(filtro);

        return result;
    }

    public List<Usuario> buscador(String filtro) {
        List<Usuario> result = repositorio.encuentraPorNombreEmailOTelefonoNativa(filtro);

        return result;
    }

    public Usuario edit(Usuario u) {
        return repositorio.save(u);
    }

    public void delete(Usuario e) {
        repositorio.delete(e);
    }


    public void saveAll(List<Usuario> lista) {
        repositorio.saveAll(lista);
    }

    @Transactional
    public void deleteAll() {
        repositorio.deleteAllInBatch();
    }

    //Login
    public Usuario registrar(Usuario u) {
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        return repositorio.save(u);
    }
    public Usuario buscarPorUsername(String username) {
        return repositorio.findFirstByUsername(username);
    }



}

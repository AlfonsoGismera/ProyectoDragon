package es.Alfonso.tienda.servicios;

import es.Alfonso.tienda.entidades.Juego;
import es.Alfonso.tienda.repositorios.JuegoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class JuegoService {

    private final JuegoRepository repositorio;


    public void add(Juego e) {
        repositorio.save(e);
    }

    public List<Juego> findAll() {
        return repositorio.findAll();
    }

    public Juego findById(long id) {
        return repositorio.findById(id).orElse(null);
    }

    public List<Juego> findByName(String filtro) {
        List<Juego> result = repositorio.findByNombreContainsIgnoreCase(filtro);

        return result;
    }

    public List<Juego> buscador(String filtro) {

        List<Juego> result = repositorio.encuentraPorNombreEmailOTelefonoNativa(filtro);

        return result;
    }



    public Juego edit(Juego e) {
        return repositorio.save(e);
    }

    public void delete(Juego e) {
        repositorio.delete(e);
    }


    public void saveAll(List<Juego> lista) {
        repositorio.saveAll(lista);
    }

    @Transactional
    public void deleteAll() {
        repositorio.deleteAllInBatch();
    }


}

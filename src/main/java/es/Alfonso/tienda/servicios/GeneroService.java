package es.Alfonso.tienda.servicios;


import es.Alfonso.tienda.repositorios.GeneroRepository;
import org.springframework.stereotype.Service;
import es.Alfonso.tienda.entidades.Genero;
import lombok.Data;


import javax.transaction.Transactional;
import java.util.List;

@Data
@Service
public class GeneroService {
    private final GeneroRepository generoService;

    public List<Genero> findAll() {
        return generoService.findAll();
    }

    public Genero findById(long id) {
        return generoService.findById(id).orElse(null);
    }

    public void saveAll(List<Genero> lista) {
        generoService.saveAll(lista);
    }

    @Transactional
    public void deleteAll() {
        generoService.deleteAllInBatch();
    }
}

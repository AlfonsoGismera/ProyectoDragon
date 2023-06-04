package es.Alfonso.tienda.servicios;


import es.Alfonso.tienda.repositorios.FaccionRepository;
import org.springframework.stereotype.Service;
import es.Alfonso.tienda.entidades.Faccion;
import lombok.Data;

import javax.transaction.Transactional;
import java.util.List;

@Data
@Service
public class FaccionService {

    private final FaccionRepository faccionService;

    public List<Faccion> findAll() {
        return faccionService.findAll();
    }

    public Faccion findById(long id) {
        return faccionService.findById(id).orElse(null);
    }

    public void saveAll(List<Faccion> lista) {
        faccionService.saveAll(lista);
    }

    @Transactional
    public void deleteAll() {
        faccionService.deleteAllInBatch();
    }
}

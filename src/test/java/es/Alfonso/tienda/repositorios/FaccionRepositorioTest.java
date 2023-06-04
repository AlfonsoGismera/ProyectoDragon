//package es.Alfonso.tienda.repositorios;
//
//import com.github.database.rider.core.api.dataset.DataSet;
//import com.github.database.rider.spring.api.DBRider;
//import es.Alfonso.tienda.entidades.Faccion;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@Slf4j
//@DataJpaTest(showSql = false)
//@DBRider
//class FaccionRepositorioTest {
//
//    public static final Long DEPTO_ID_1 = 1L;
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Autowired
//    private FaccionRepository faccionRepository;
//
////  @BeforeEach
////  @DataSet("datasets/faccion.yml")
////  public void setUp() {}
//
//    @Test
//    void should_find_no_tutorials_if_repository_is_empty() {
//        Iterable faccion = faccionRepository.findAll();
//
//        assertThat(faccion).isEmpty();
//    }
//
//    @Test
//    void should_persist() {
//        // Transient
//        Faccion faccion = Faccion.builder()
//                .nombre("IMPERIO")
//                .build();
//        log.info("Faccion transient {}", faccion);
//
//        // Persist
//        entityManager.persist(faccion);
//        // Observar que ya tiene id porque al ser sequence ha ido a la base de datos a preguntar
//        // por el siguiente id. Si hubiera sido identity habría tenido que insertar un registro en BD
//        // para conocer el id asignado
//        log.info("Faccion después de persist {}", faccion);
//        assertThat(faccion.getId()).isNotNull();
//
//        // getId
//        Faccion faccionInContext = entityManager.find(Faccion.class, faccion.getId());
//        // Comprobamos que Hibernate devuelve el mismo objeto managed que ya existe en el contexto.
//        // Se obtiene de la caché de primer nivel, y find no tiene que realizar una consulta SELECT.
//        log.info("Faccion in context {}", faccionInContext);
//        assertThat(faccionInContext).isEqualTo(faccion);
//
//        // Flush
//        faccion.setNombre("entity is managed");
//        entityManager.flush();
//        // Observar que Hibernate hace un INSERT porque estaba en contexto pero no en BS
//        // y después hace un UPDATE.
//        log.info("Faccion después de flush {}", faccion);
//    }
//
//    @Test
//    @DataSet("datasets/faccion.yml")
//    void should_detach() {
//        Faccion depDetached = entityManager.find(Faccion.class, DEPTO_ID_1); // Informática
//        depDetached.setNombre("detached!!");
//        entityManager.detach(depDetached);
//        entityManager.flush();
//        // Después de desligar depDetached, podemos comprobar que flush no lanzará la sentencia UPDATE
//        // para modificar el nombre.
//        log.info("Faccion después de detach y flush {}", depDetached);
//
//        Faccion faccion = entityManager.find(Faccion.class, DEPTO_ID_1);
//        log.info("Faccion después de find {}", faccion);
//        assertThat(depDetached).isNotEqualTo(faccion);
//        // En la base de datos sigue teniendo el nombre de Informática
//        assertThat(faccion.getNombre()).isEqualTo("IMPERIO");
//    }
//
//    @Test
//    @DataSet("datasets/faccion.yml")
//    void should_remove() {
//        Faccion depRemove = entityManager.find(Faccion.class, DEPTO_ID_1);
//        entityManager.remove(depRemove);
//        log.info("Faccion después de remove {}", depRemove);
//        // La línea siguiente demuestra que el depto con identificador 1 ya no existe para el contexto,
//        // a pesar de que todavía no se ha ejecutado el DELETE.
//        assertThat(entityManager.find(Faccion.class, DEPTO_ID_1));
//        entityManager.flush();
//    }
//
//    @Test
//    @DataSet("datasets/faccion.yml")
//    void should_merge_detached() {
//        Faccion depDetached = entityManager.find(Faccion.class, DEPTO_ID_1);
//        entityManager.detach(depDetached);
//        log.info("Faccion detached {}", depDetached);
//
//        depDetached.setNombre("merged!!");
//        Faccion depManaged = entityManager.merge(depDetached);
//        log.info("Faccion detached después de merge {} {}", depDetached.hashCode(), depDetached);
//        log.info("Faccion managed después de merge {} {} ", depManaged.hashCode(), depManaged);
//        // La llamada a merge devuelve un objeto managed distinto al desligado pero con el mismo contenido.
//        assertThat(depManaged).isNotEqualTo(depDetached);
//        assertThat(depManaged.getNombre()).isEqualTo(depDetached.getNombre());
//
//        // Al hacer flush, los cambios en la entidad managed, y, atención,
//        // que fueron realizados en el objeto desligado, se persistirán con el consabido UPDATE.
//        entityManager.flush();
//    }
//
//}
//

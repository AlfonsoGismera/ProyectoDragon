//package es.Alfonso.tienda.repositorios;
//
//import com.github.database.rider.core.api.dataset.DataSet;
//import com.github.database.rider.spring.api.DBRider;
//import es.Alfonso.tienda.entidades.Proyecto;
//import es.Alfonso.tienda.entidades.Usuario;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@Slf4j
//@DataJpaTest(showSql = false)
//@DBRider
//class UsuarioRepositoryTest {
//
//    public static final Long USER_ID_1 = 1L;
//    public static final Long USER_ID_3 = 3L;
//    public static final Long PRJ_ID_1 = 1L;
//
//    @Autowired
//    private UsuarioRepository usuarioRepository;
//
//    @Autowired
//    private ProyectoRepository proyectoRepository;
//    @Autowired
//    private UsuarioPrefeRepository usuarioPrefeRepository;
//
//    @Test
//    void should_findByNombreContainsIgnoreCase() {
//
//        Usuario e = Usuario.builder()
//                .nombre("Alfonso Gismera")
//                .imagen("../img/imperio.jpg")
//                .email("alfonso.gismera@warhhamer.com")
//                .telefono("123456789")
//                .directivo(true)
//                .build();
//
//        usuarioRepository.save(e);
//
//        List<Usuario> resultados = usuarioRepository.findByNombreContainsIgnoreCase("gismera");
//        int numUsuario = 1;
//        assertThat(resultados).hasSize(numUsuario);
//    }
//
//    @Test
//    void should_findByNombreStartingWithIgnoreCase() {
//
//        Usuario e = Usuario.builder()
//                .nombre("Alfonso Gismera")
//                .imagen("../img/imperio.jpg")
//                .email("alfonso.gismera@warhhamer.com")
//                .telefono("123456789")
//                .directivo(true)
//                .build();
//
//        usuarioRepository.save(e);
//
//        List<Usuario> resultados = usuarioRepository.findByNombreContainsIgnoreCase("gismera");
//        int numUsuario = 1;
//        assertThat(resultados).hasSize(numUsuario);
//    }
//
//    @Test
//    void should_deleteAll() {
//        Usuario e1 = Usuario.builder()
//                .nombre("Alfonso Gismera")
//                .imagen("../img/imperio.jpg")
//                .email("alfonso.gismera@warhhamer.com")
//                .telefono("123456789")
//                .directivo(true)
//                .build();
//
//        Usuario e2 = Usuario.builder()
//                .nombre("Alfonso Gismera")
//                .imagen("../img/imperio.jpg")
//                .email("alfonso.gismera@warhhamer.com")
//                .telefono("123456789")
//                .directivo(true)
//                .build();
//
//        usuarioRepository.save(e1);
//        usuarioRepository.save(e2);
//
//        usuarioRepository.deleteAll();
//
//        assertThat(usuarioRepository.findAll()).isEmpty();
//    }
//
//    @Test
//    void should_deleteAllInBatch() {
//        Usuario e1 = Usuario.builder()
//                .nombre("Alfonso Gismera")
//                .imagen("../img/imperio.jpg")
//                .email("alfonso.gismera@warhhamer.com")
//                .telefono("123456789")
//                .directivo(true)
//                .build();
//
//        Usuario e2 = Usuario.builder()
//                .nombre("Alfonso Gismera")
//                .imagen("../img/imperio.jpg")
//                .email("alfonso.gismera@warhhamer.com")
//                .telefono("123456789")
//                .directivo(true)
//                .build();
//
//        usuarioRepository.save(e1);
//        usuarioRepository.save(e2);
//
//        usuarioRepository.deleteAll();
//
//        assertThat(usuarioRepository.findAll()).isEmpty();
//    }
//
//    @Test
//    @DataSet(value = {"datasets/proyectos.yml", "datasets/faccion.yml", "datasets/usuarios.yml"})
//    void should_updateEmpleado () {
//
//        Usuario emp1 = usuarioRepository.findById(USER_ID_1).orElse(null);
//        Proyecto prj1 = proyectoRepository.findById(PRJ_ID_1).orElse(null);
//
//        emp1.getProyectos().add(prj1);
//        usuarioRepository.save(emp1);
//
//        log.info("antes del assert");
//        Optional<Usuario> emp2 = usuarioRepository.findById(USER_ID_1);
//        if (emp2.isPresent()) {
//            assertThat(emp2.get().getProyectos()).hasSize(3);
//        }
//
//    }
//
//    @Test
//    @DataSet(value = {"datasets/proyectos.yml"})
//    void should_insertEmpleadoAndAddToProject () {
//        Usuario emp1 = Usuario.builder()
//                .nombre("Alfonso Gismera")
//                .imagen("../img/imperio.jpg")
//                .email("alfonso.gismera@warhhamer.com")
//                .telefono("123456789")
//                .directivo(true)
//                .build();
//
//        Proyecto prj1 = proyectoRepository.findById(PRJ_ID_1).orElse(null);
//
//        emp1.setProyectos(List.of(prj1));
//        // Probar sin y con la siguiente sentencia y ver en el depurador
//        // prj1.getEmpleados().add(emp1);
//        // Funciona el test en ambos casos pero con la sentencia
//        // la relación al nivel del contexto se ve consistente en ambos sentidos de la relación
//        usuarioRepository.save(emp1);
//
//        Usuario emp2 = usuarioRepository.findByNombre("Alfonso");
//
//        if (emp2 != null) {
//            assertThat(emp2.getProyectos()).hasSize(1);
//        }
//
//    }
//
//
//}

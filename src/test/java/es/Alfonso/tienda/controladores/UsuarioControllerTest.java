//package es.Alfonso.tienda.controladores;
//
//import es.Alfonso.tienda.entidades.Faccion;
//import es.Alfonso.tienda.entidades.Usuario;
//import es.Alfonso.tienda.servicios.FaccionService;
//import es.Alfonso.tienda.servicios.I18nService;
//import es.Alfonso.tienda.servicios.UsuarioService;
//import es.Alfonso.tienda.storage.StorageService;
//import org.hamcrest.Matchers;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//
//@WebMvcTest(controllers = UserController.class,
//        excludeAutoConfiguration = SecurityAutoConfiguration.class)
//class UsuarioControllerTest {
//
//    @MockBean
//    private UsuarioService usuarioService;
//    @MockBean
//    private FaccionService faccionService;
//
//    @MockBean
//    private StorageService storageService;
//    @MockBean
//    private I18nService servicioInternacionalizacion;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//
//    @Test
//    void listado_shouldReturnViewWithPrefilledData() throws Exception {
//
//        Faccion dep3 = Faccion.builder()
//                .nombre("User")
//                .build();
//
//        // Crear una lista de empleados con un usuarios para el test
//        Usuario e = Usuario.builder()
//                .id(2)
//                .username("user")
//                .password(passwordEncoder.encode("user"))
//                .role("ROLE_USER")
//                .nombre("Javier Jimenez")
//                .imagen("../img/tirass.png")
//                .email("javier.jimenez@warhhamer.com")
//                .telefono("954000000")
//                .faccion(dep3)
//                .build();
//        List<Usuario> lista = Arrays.asList(e);
//        // Cuando se invoque el servicio findAll() que devuelva esta lista recién creada
//        when(usuarioService.findAll()).thenReturn(lista);
//        // Simulamos la llamada al controlador y posterior creación de la vista
//        mockMvc.perform(MockMvcRequestBuilders.get("/usuario/list"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.view().name("list"))
//                .andExpect(MockMvcResultMatchers.model().attribute("listaUsuarios",
//                        Matchers.contains(e)));
//
//    }
//}

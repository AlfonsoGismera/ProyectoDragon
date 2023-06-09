package es.Alfonso.tienda.config;


import es.Alfonso.tienda.entidades.*;
import es.Alfonso.tienda.repositorios.PreferenciaRepository;
import es.Alfonso.tienda.servicios.*;
import es.Alfonso.tienda.storage.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Slf4j
@Configuration
@EnableJpaAuditing
public class InitialDataConfiguration {

    @Autowired
    private PreferenciaRepository preferenciaRepository;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    FaccionService faccionService;

    @Autowired
    JuegoService juegoService;

    @Autowired
    GeneroService generoService;

    @Autowired
    StorageService storageService;


    @Autowired
    private PreferenciasService preferenciasService;


    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostConstruct
    public void initStorage() {
        storageService.deleteAll();
        storageService.init();
    }


    @PostConstruct
    public void initFaccion() {
        preferenciasService.deleteAll();
        usuarioService.deleteAll();
        faccionService.deleteAll();

        Faccion fac1 = Faccion.builder()
                .nombre("Directivo")
                .id(1)
                .build();

        Faccion fac2 = Faccion.builder()
                .nombre("Programador")
                .id(2)
                .build();
        Faccion fac3 = Faccion.builder()
                .nombre("Usuario")
                .id(3)
                .build();
        Faccion fac4 = Faccion.builder()
                .nombre("Diseñador")
                .id(4)
                .build();


        log.info("alta facciones");
        faccionService.saveAll(Arrays.asList(fac1, fac2, fac3, fac4));

        log.info("alta usuarios");

        Usuario usr1 = Usuario.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .role("ROLE_ADMIN")
                .nombre("Alfonso Gismera")
                .imagen("../img/imperio.jpg")
                .email("alfonso.gismera@warhhamer.com")
                .telefono("123456789")
                .faccion(fac1)
                .build();


        Usuario usr2 = Usuario.builder()
                .username("user")
                .password(passwordEncoder.encode("user"))
                .role("ROLE_USER")
                .nombre("Alberto Jimenez")
                .imagen("../img/ork.jpg")
                .email("alberto.jimenez@warhhamer.com")
                .telefono("954000000")
                .faccion(fac3)
                .build();

      Usuario usr3 = Usuario.builder()
                .username("jav")
                .password(passwordEncoder.encode("jav"))
                .role("ROLE_USER")
                .nombre("Javier Jimenez")
                .imagen("../img/tirass.png")
                .email("javier.jimenez@warhhamer.com")
                .telefono("954000000")
                .faccion(fac2)
                .build();
        Usuario usr4 = Usuario.builder()
                .username("ser")
                .password(passwordEncoder.encode("ser"))
                .role("ROLE_ADMIN")
                .nombre("Sergio Jimenez")
                .imagen("../img/Avatar_nuevo.jpg")
                .email("sergio.jimenez@warhhamer.com")
                .telefono("954000000")
                .faccion(fac4)
                .build();
        //Guardamos las preferencias, tienes que guardarlas antes de la lista o casca
        Preferencias prefs1 = Preferencias.builder().idioma("fr_FR").usuario(usr1).build();
        preferenciasService.save(prefs1);
        Preferencias prefs2 = Preferencias.builder().idioma("es_ES").usuario(usr2).build();
        preferenciasService.save(prefs2);

        usuarioService.saveAll(Arrays.asList(usr1, usr2, usr3, usr4));


    }

    @PostConstruct
    public void initGenero() {


        juegoService.deleteAll();
        generoService.deleteAll();

        Genero gen1 = Genero.builder()
                .nombre("Aventura")
                .id(1)
                .build();

        Genero gen2 = Genero.builder()
                .nombre("Estrategia")
                .id(2)
                .build();
        Genero gen3 = Genero.builder()
                .nombre("Rol")
                .id(3)
                .build();
        Genero gen4 = Genero.builder()
                .nombre("WarGame")
                .id(4)
                .build();


        log.info("alta generos");
        generoService.saveAll(Arrays.asList(gen1, gen2, gen3, gen4));

        log.info("alta Juegos");

        Juego jue1 = Juego.builder()
                .nombre("Catan")
                .imagen("/img/juegos/catan.jpg")
                .empresa("Malta")
                .precio("34")
                .disponible(true)
                .genero(gen1)
                .build();


        Juego jue2 = Juego.builder()
                .nombre("Carcasone")
                .imagen("/img/juegos/carcasone.jpg")
                .empresa("Malta")
                .precio("40")
                .disponible(false)
                .genero(gen2)
                .build();

        Juego jue3 = Juego.builder()
                .nombre("7 de Mar")
                .imagen("/img/juegos/naciones-piratas.jpg")
                .empresa("Primin")
                .precio("15")
                .disponible(false)
                .genero(gen2)
                .build();

        Juego jue4 = Juego.builder()
                .nombre("zombicideBP")
                .imagen("/img/juegos/zombicideBP.jpg")
                .empresa("Vasili")
                .precio("32")
                .disponible(true)
                .genero(gen3)
                .build();
        Juego jue5 = Juego.builder()
                .nombre("Oathsworn")
                .imagen("/img/juegos/oathsworn.jpg")
                .empresa("Vasili")
                .precio("18")
                .disponible(true)
                .genero(gen3)
                .build();
        Juego jue6 = Juego.builder()
                .nombre("7 de Mar Corsarios")
                .imagen("https://www.nosolorol.com/913-large_default/7-mar-pantalla-del-dj-papel.jpg")
                .empresa("Primin")
                .precio("25")
                .disponible(true)
                .genero(gen2)
                .build();
        Juego jue7 = Juego.builder()
                .nombre("Mars")
                .imagen("/img/juegos/mars.jpg")
                .empresa("Primin")
                .precio("35")
                .disponible(true)
                .genero(gen2)
                .build();

        Juego jue8 = Juego.builder()
                .nombre("La tripulación")
                .imagen("/img/juegos/agua.jpg")
                .empresa("Primin")
                .precio("18")
                .disponible(true)
                .genero(gen2)
                .build();
        Juego jue9 = Juego.builder()
                .nombre("Dune")
                .imagen("https://www.nosolorol.com/2434-large_default/dune-aventuras-en-el-imperio-papel.jpg")
                .empresa("Falcon")
                .precio("32")
                .disponible(true)
                .genero(gen4)
                .build();
        Juego jue10 = Juego.builder()
                .nombre("Root")
                .imagen("/img/juegos/root.jpg")
                .empresa("Falcon")
                .precio("54")
                .disponible(true)
                .genero(gen1)
                .build();




        juegoService.saveAll(Arrays.asList(jue1, jue2, jue3, jue4,jue5,jue6,jue7,jue8,jue9,jue10));


    }


}

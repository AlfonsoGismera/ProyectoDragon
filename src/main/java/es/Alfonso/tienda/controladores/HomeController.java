package es.Alfonso.tienda.controladores;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.NoHandlerFoundException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;


@Slf4j
@Controller
@RequestMapping("/")
public class HomeController {
    public static final String CONTADOR_USUARIOS = "num_Usuarios";
@GetMapping({"", "/Pruebas"})
public String Pruebas(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
    log.info("Página de bienvenida");

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!(authentication instanceof AnonymousAuthenticationToken)) {
        String usuario = authentication.getName();

        HttpSession session2 = request.getSession();
        boolean primeraVez = (session2.getAttribute(CONTADOR_USUARIOS) == null);

        // Comprobar si el navegador tenía cookie del usuario
        if (primeraVez) {
            Optional<Cookie> cookieEncontrada1 = Arrays.stream(request.getCookies())
                    .filter(cookie -> usuario.equals(cookie.getName()))
                    .findAny();

            // si no existe la cookie el contador de visitas se pone a 1

            if (cookieEncontrada1.isEmpty()) {  // si no existe la cookie >> primera visita
                Cookie cookie = new Cookie(usuario, "1");
                cookie.setPath("/");
                cookie.setDomain("localhost");
                cookie.setMaxAge(7 * 24 * 60 * 60);  // 7 días
                cookie.setSecure(true);
                cookie.setHttpOnly(true);

                response.addCookie(cookie);

            } else {  // si existe la cookie se recupera el contador y se le suma 1
                Cookie cookie = cookieEncontrada1.get();
                int contador = Integer.parseInt(cookie.getValue());
                cookie.setValue(String.valueOf(contador + 1));
                response.addCookie(cookie);
            }
        }
    }
//    Esto es para q en index no nos lo muestre null
    Object contadorusuario = session.getAttribute(CONTADOR_USUARIOS);
    if (contadorusuario == null)
        session.setAttribute(CONTADOR_USUARIOS, 4);
    return "Pruebas";
}



    @GetMapping("/noticias")
    public String humanidad(HttpServletRequest request, HttpServletResponse response) {
        log.info("Página de Noticias");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String usuario = authentication.getName();

            // Comprobar si el navegador tenía cookie del usuario
            Optional<Cookie> cookieEncontrada = Arrays.stream(request.getCookies())
                    .filter(cookie -> usuario.equals(cookie.getName()))
                    .findAny();

            // si no existe la cookie el contador de visitas se pone a 1
            if (cookieEncontrada.isEmpty()) {  // si no existe la cookie >> primera visita
                Cookie cookie = new Cookie(usuario, "1");
                cookie.setPath("/");
                cookie.setDomain("localhost");
                cookie.setMaxAge(7 * 24 * 60 * 60);  // 7 días
                cookie.setSecure(true);
                cookie.setHttpOnly(true);

                response.addCookie(cookie);

            } else {  // si existe la cookie se recupera el contador y se le suma 1
                Cookie cookie = cookieEncontrada.get();
                int contador = Integer.parseInt(cookie.getValue());
                cookie.setValue(String.valueOf(contador + 1));
                response.addCookie(cookie);
            }
        }
        return "noticias";
    }
    @GetMapping("/novedades")
    public String novedades(HttpServletRequest request, HttpServletResponse response) {
        log.info("Página de Noticias");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String usuario = authentication.getName();

            // Comprobar si el navegador tenía cookie del usuario
            Optional<Cookie> cookieEncontrada = Arrays.stream(request.getCookies())
                    .filter(cookie -> usuario.equals(cookie.getName()))
                    .findAny();

            // si no existe la cookie el contador de visitas se pone a 1
            if (cookieEncontrada.isEmpty()) {  // si no existe la cookie >> primera visita
                Cookie cookie = new Cookie(usuario, "1");
                cookie.setPath("/");
                cookie.setDomain("localhost");
                cookie.setMaxAge(7 * 24 * 60 * 60);  // 7 días
                cookie.setSecure(true);
                cookie.setHttpOnly(true);

                response.addCookie(cookie);

            } else {  // si existe la cookie se recupera el contador y se le suma 1
                Cookie cookie = cookieEncontrada.get();
                int contador = Integer.parseInt(cookie.getValue());
                cookie.setValue(String.valueOf(contador + 1));
                response.addCookie(cookie);
            }
        }
        return "novedades";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/noticias1")
    public String humanidad1() {
        return "noticias1";
    }
    @GetMapping("/noticias2")
    public String humanidad2() {
        return "noticias2";
    }
    @GetMapping("/contacto")
    public String contacto() {
        return "contacto";
    }

    @GetMapping("/nosotrost")
    public String nosotrost() {
        return "nosotrost";
    }

    @GetMapping("/privacidadp")
    public String privacidadp() {
        return "privacidadp";
    }

    @GetMapping("/forbidden")
    public String forbidden() {
        return "forbidden";
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNotFoundError(HttpServletResponse response) throws IOException {
        response.sendRedirect("/forbidden");
        return "forbidden";
    }


}


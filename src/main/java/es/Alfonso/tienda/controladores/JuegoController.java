package es.Alfonso.tienda.controladores;

import es.Alfonso.tienda.entidades.Genero;
import es.Alfonso.tienda.entidades.Juego;
import es.Alfonso.tienda.entidades.Usuario;
import es.Alfonso.tienda.servicios.GeneroService;
import es.Alfonso.tienda.servicios.I18nService;
import es.Alfonso.tienda.servicios.JuegoService;
import es.Alfonso.tienda.storage.StorageService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@Controller
@Data
@Slf4j
@RequestMapping("/juego")
public class JuegoController {

    private final JuegoService juegoService;

    private final GeneroService generoService;
    private final StorageService storageService;

    private final I18nService servicioInternacionalizacion;

    @ModelAttribute("listaGenero")
    public List<Genero> listaGenero() {
        return generoService.findAll();
    }

    @GetMapping("/listJuego")
    public String listado(@RequestParam(name = "q", required = false) String query, Model model) {
        model.addAttribute("listaJuegos", (query == null) ? juegoService.findAll() : juegoService.buscador(query));

        return "listJuego";
    }

    @GetMapping("/listJuego/filter")
    public String listadoFiltrado(@RequestParam("nombre") String nombre, Model model) {
        model.addAttribute("listaJuegos", juegoService.findByName(nombre));
        return "fragmentos::lista-juegos";
    }

    @GetMapping("/new")
    public String nuevoJuegoForm(Model model) {
        model.addAttribute("JuegosForm", new Juego());
        return "formJuego";
    }

    @PostMapping("/new/submit")
    public String nuevoJuegoSubmit(@Valid @ModelAttribute("JuegoForm") Juego nuevoJuego,
                                   BindingResult bindingResult, @RequestParam("file") MultipartFile file, HttpSession session, Model model) {

        if (bindingResult.hasErrors()) {
            log.info("hay errores en el formulario");
            bindingResult.getFieldErrors()
                    .forEach(e -> log.info("field: " + e.getField() + ", rejected value: " + e.getRejectedValue()));
            return "formJuegos";
        } else {
            if (!file.isEmpty()) {
                log.info("hay imagen");
                String avatar = storageService.store(file, nuevoJuego.getId());
                nuevoJuego.setImagen(MvcUriComponentsBuilder
                        .fromMethodName(UserController.class, "serveFile", avatar).build().toUriString());
                log.info("imagen {}", nuevoJuego.getImagen());
            }
            juegoService.add(nuevoJuego);


            return "redirect:/juego/listJuego";
        }

    }

    @GetMapping("/edit/{id}")
    public String editarJuegoForm(@PathVariable long id, Model model, HttpSession session) {

        Juego juego = juegoService.findById(id);
        if (juego != null) {
            model.addAttribute("JuegosForm", juego);
            return "formJuego";
        } else {

            return "redirect:/juego/new";
        }
    }

    @PostMapping("/edit/submit")
    public String editarJuegoSubmit(@Valid @ModelAttribute("JuegosForm") Juego juego,
                                    @RequestParam("file") MultipartFile file,
                                    BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "formJuego";
        } else {
            if (!file.isEmpty()) {
                log.info("hay imagen");
                String avatar = storageService.store(file, juego.getId());
                juego.setImagen(MvcUriComponentsBuilder
                        .fromMethodName(UserController.class, "serveFile", avatar).build().toUriString());
                log.info("imagen {}", juego.getImagen());
            }
            juegoService.edit(juego);
            return "redirect:/juego/listJuego";
        }

    }


    @GetMapping("/delete/{id}")
    public String borrarJuego(@PathVariable("id") Long id, Model model, HttpSession session) {

        Juego juego = juegoService.findById(id);
        if (juego != null)
            juegoService.delete(juego);
        return "redirect:/juego/listJuego";
    }

    @GetMapping("/delete/show/{id}")
    public String showModalBorrarJuego(@PathVariable("id") Long id, Model model) {

        Juego juego = juegoService.findById(id);
        String deleteMessage = "";
        if (juego != null)
            deleteMessage = servicioInternacionalizacion.getMessage("usuario.borrar.mensaje",
                    new Object[]{juego.getNombre()});
        else
            return "redirect:/juego/?error=true";

        model.addAttribute("delete_url", "/juego/delete/" + id);
        model.addAttribute("delete_title",
                servicioInternacionalizacion.getMessage("usuario.borrar.titulo"));
        model.addAttribute("delete_message", deleteMessage);
        return "fragmentos::modal-borrar";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().body(file);
    }


}

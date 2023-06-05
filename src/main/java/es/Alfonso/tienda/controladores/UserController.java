package es.Alfonso.tienda.controladores;

import es.Alfonso.tienda.entidades.Faccion;
import es.Alfonso.tienda.entidades.Usuario;
import es.Alfonso.tienda.servicios.FaccionService;
import es.Alfonso.tienda.servicios.UsuarioService;
import es.Alfonso.tienda.storage.StorageService;
import es.Alfonso.tienda.servicios.I18nService;
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
@RequestMapping("/usuario")
public class UserController {

    private final UsuarioService usuarioService;

    private final FaccionService faccionService;
    private final StorageService storageService;

    private final I18nService servicioInternacionalizacion;

    public String CONTADOR_USUARIOS = "num_Usuarios";

    @ModelAttribute("listaFaccion")
    public List<Faccion> listaFaccion() {
        return faccionService.findAll();
    }

    @GetMapping("/list")
    public String listado(@RequestParam(name = "q", required = false) String query, Model model) {
        model.addAttribute("listaUsuarios", (query == null) ? usuarioService.findAll() : usuarioService.buscador(query));

        return "list";
    }

    @GetMapping("/list/filter")
    public String listadoFiltrado(@RequestParam("nombre") String nombre, Model model) {
        model.addAttribute("listaUsuarios", usuarioService.findByName(nombre));
        return "fragmentos::lista-usuarios";
    }

    @GetMapping("/new")
    public String nuevoUsuarioForm(Model model) {
        model.addAttribute("UsuariosForm", new Usuario());
        return "form";
    }

    @PostMapping("/new/submit")
    public String nuevoUsuarioSubmit(@Valid @ModelAttribute("UsuariosForm") Usuario nuevoUsuario,
                                     BindingResult bindingResult, @RequestParam("file") MultipartFile file, HttpSession session, Model model) {

        if (bindingResult.hasErrors()) {
            log.info("hay errores en el formulario");
            bindingResult.getFieldErrors()
                    .forEach(e -> log.info("field: " + e.getField() + ", rejected value: " + e.getRejectedValue()));
            return "form";
        } else {
            if (!file.isEmpty()) {
                log.info("hay imagen");
                String avatar = storageService.store(file, nuevoUsuario.getId());
                nuevoUsuario.setImagen(MvcUriComponentsBuilder
                        .fromMethodName(UserController.class, "serveFile", avatar).build().toUriString());
                log.info("imagen {}", nuevoUsuario.getImagen());
            }
            usuarioService.registrar(nuevoUsuario);

            Object contadorusuario = session.getAttribute(CONTADOR_USUARIOS);
            if (contadorusuario == null)
                session.setAttribute(CONTADOR_USUARIOS, 4);
            else
                session.setAttribute(CONTADOR_USUARIOS, (int) contadorusuario + 1);
            return "redirect:/usuario/list";
        }

    }

    @GetMapping("/edit/{id}")
    public String editarUsuarioForm(@PathVariable long id, Model model, HttpSession session) {

        Usuario usuario = usuarioService.findById(id);
        if (usuario != null) {
            model.addAttribute("UsuariosForm", usuario);
            usuarioService.edit(usuario);
            return "form";

        } else {
            return "redirect:/usuario/new";
        }
    }

    @PostMapping("/edit/submit")
    public String editarUsuarioSubmit(@Valid @ModelAttribute("UsuariosForm") Usuario usuario,
                                      @RequestParam("file") MultipartFile file,
                                      BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "form";
        } else {
            if (!file.isEmpty()) {
                log.info("hay imagen");
                String avatar = storageService.store(file, usuario.getId());
                usuario.setImagen(MvcUriComponentsBuilder
                        .fromMethodName(UserController.class, "serveFile", avatar).build().toUriString());
                log.info("imagen {}", usuario.getImagen());
            }
            usuarioService.edit(usuario);
            return "redirect:/usuario/list";
        }

    }


    @GetMapping("/delete/{id}")
    public String borrarUsuario(@PathVariable("id") Long id, Model model, HttpSession session) {

        Usuario usuario = usuarioService.findById(id);
        Object contadorusuario = session.getAttribute(CONTADOR_USUARIOS);
        if (usuario != null)
            usuarioService.delete(usuario);
        session.setAttribute(CONTADOR_USUARIOS, (int) contadorusuario - 1);
        return "redirect:/usuario/list";
    }

    @GetMapping("/delete/show/{id}")
    public String showModalBorrarEmpleado(@PathVariable("id") Long id, Model model) {

        Usuario usuario = usuarioService.findById(id);
        String deleteMessage = "";
        if (usuario != null)
            deleteMessage = servicioInternacionalizacion.getMessage("usuario.borrar.mensaje",
                    new Object[]{usuario.getNombre()});
        else
            return "redirect:/usuario/?error=true";

        model.addAttribute("delete_url", "/usuario/delete/" + id);
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

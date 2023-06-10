package es.Alfonso.tienda.controladores;

import es.Alfonso.tienda.entidades.Preferencias;
import es.Alfonso.tienda.entidades.Usuario;
import es.Alfonso.tienda.servicios.PreferenciasService;
import es.Alfonso.tienda.servicios.UsuarioService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@Data
@Slf4j
@RequestMapping("/prefs")
public class PreferenciasController {

    private final PreferenciasService preferenciasService;
    private final UsuarioService usuarioService;

    @GetMapping("/edit")
    public String editPreferenciasForm(Model model) {

        Preferencias preferencias = preferenciasService.findUsuarioPreferencias().orElse(null);
        model.addAttribute("preferenciasForm", preferencias);
        model.addAttribute("opcionesIdioma", Map.of("es_ES", "Español", "fr_FR", "Inglés"));
        return "prefs-form";
    }

    @PostMapping("/edit/submit")
    public String editarPreferenciasSubmit(@ModelAttribute("preferenciasForm") Preferencias preferencias) {
        log.info("Preferencias {}", preferencias);
        Usuario usuario = usuarioService.findById(preferencias.getId());
        preferencias.setUsuario(usuario);
        log.info("Preferencias Paso 2 {}", preferencias);
        preferenciasService.saveAuth(preferencias);
        return "redirect:/usuario/list?lang=" + preferencias.getIdioma();
    }

    @GetMapping(value = "/list", produces = "application/json")
    public ResponseEntity<Object> listaPreferencias() {
        Preferencias prefs = preferenciasService.findUsuarioPreferencias().orElse(null);
        return ResponseEntity.ok().body(Map.of(
                "idioma", prefs.getIdioma()));
    }
}

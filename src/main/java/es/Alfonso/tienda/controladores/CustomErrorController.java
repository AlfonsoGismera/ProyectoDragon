package es.Alfonso.tienda.controladores;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @GetMapping(ERROR_PATH)
    public String handleError() {
        return "forbidden";
    }

    public String getErrorPath() {
        return ERROR_PATH;
    }
}

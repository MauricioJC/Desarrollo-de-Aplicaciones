package Aplicacion;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Creado por Mauricio el 27/04/2017.
 **/
@Controller
public class templateController {

    @RequestMapping("*")
    public String defecto(){
        return "paginaInicio";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String obtenerPaginaInicio(){
        return "paginaInicio";
    }

    @RequestMapping(value = "smartphones", method = RequestMethod.GET)
    public String obtenerPaginaSmartphones(){
        return "paginaSmartphones";
    }

    @RequestMapping(value = "ventas", method = RequestMethod.GET)
    public String obtenerPaginaVentas(){
        return "paginaVentas";
    }

    @RequestMapping(value = "traspasos", method = RequestMethod.GET)
    public String obtenerPaginaDeTraspasos(){
        return "paginaTraspasos";
    }

    @RequestMapping(value = "reservaciones", method = RequestMethod.GET)
    public String obtenerPaginaDeReservaciones(){
        return "paginaReservaciones";
    }

    @RequestMapping(value = "adquisiciones", method = RequestMethod.GET)
    public String obtenerPaginaDeAdquisicion(){
        return "paginaAdquisiciones";
    }
}

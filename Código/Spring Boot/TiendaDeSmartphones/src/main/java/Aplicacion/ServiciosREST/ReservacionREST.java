package Aplicacion.ServiciosREST;

import Aplicacion.Modelos.ReservacionEntity;
import Aplicacion.Repository.ReservacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Creado por Mauricio el 05/06/2017.
 **/

@RestController
public class ReservacionREST {
    @Autowired
    private ReservacionRepository reservacionRepository;

    @RequestMapping(value = "/reservacion/lista", method = RequestMethod.GET)
    public List<ReservacionEntity> obtenerListaDeReservaciones(){
        return (List<ReservacionEntity>) this.reservacionRepository.findAll();
    }

    @RequestMapping(value = "/reservacion/eliminar", method = RequestMethod.POST)
    public void eliminarReservacion(@RequestParam("id")Integer id){
        this.reservacionRepository.delete(id);
    }
}

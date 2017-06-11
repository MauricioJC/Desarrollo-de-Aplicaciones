package Aplicacion.ServiciosREST;

import Aplicacion.Modelos.ReservacionEntity;
import Aplicacion.Modelos.SmartphoneEntity;
import Aplicacion.Modelos.UsuarioEntity;
import Aplicacion.Modelos.VentaEntity;
import Aplicacion.Repository.ReservacionRepository;
import Aplicacion.Repository.SmartphoneRepository;
import Aplicacion.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Creado por Mauricio el 05/06/2017.
 **/

@RestController
public class ReservacionREST {
    @Autowired
    private ReservacionRepository reservacionRepository;
    @Autowired
    private SmartphoneRepository smartphoneRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;


    @RequestMapping(value = "/reservacion/lista", method = RequestMethod.GET)
    public List<ReservacionEntity> obtenerListaDeReservaciones(){
        return (List<ReservacionEntity>) this.reservacionRepository.findAll();
    }

    @RequestMapping(value = "/reservacion/eliminar", method = RequestMethod.POST)
    public void eliminarReservacion(@RequestParam("id")Integer id){
        this.reservacionRepository.delete(id);
    }

    @CrossOrigin
    @RequestMapping(value = "/reservacion/registrar", method = RequestMethod.POST)
    public boolean registrarReservacion(@RequestParam("idSmartphone")Integer idSmartphone,
                                     @RequestParam("idUsuario")Integer idUsuario){
        SmartphoneEntity smartphone = this.smartphoneRepository.findByIdSmartphone(idSmartphone);
        UsuarioEntity usuario = this.usuarioRepository.findOne(idUsuario);
        ReservacionEntity reservacion = new ReservacionEntity();
        reservacion.setSmartphone(smartphone);
        reservacion.setUsuario(usuario);
        reservacion.setFecha(new Date(new GregorianCalendar().getTimeInMillis()));
        this.reservacionRepository.save(reservacion);
        return true;
    }
}

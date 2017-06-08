package Aplicacion.ServiciosREST;

import Aplicacion.Modelos.TraspasoEntity;
import Aplicacion.Repository.TraspasoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TraspasoREST {

    @Autowired
    private TraspasoRepository traspasoRepository;

    @RequestMapping(value = "/traspaso/lista", method = RequestMethod.GET)
    public List<TraspasoEntity> obtenerListaDeTraspasos(){
        return (List<TraspasoEntity>) this.traspasoRepository.findAll();
    }



}

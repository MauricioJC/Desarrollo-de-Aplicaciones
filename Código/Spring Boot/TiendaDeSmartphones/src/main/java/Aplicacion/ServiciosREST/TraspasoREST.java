package Aplicacion.ServiciosREST;

import Aplicacion.Modelos.SmartphoneEntity;
import Aplicacion.Modelos.TraspasoEntity;
import Aplicacion.Repository.SmartphoneRepository;
import Aplicacion.Repository.TraspasoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

@RestController
public class TraspasoREST {
    @Autowired
    private TraspasoRepository traspasoRepository;
    @Autowired
    private SmartphoneRepository smartphoneRepository;

    @RequestMapping(value = "/traspaso/lista", method = RequestMethod.GET)
    public List<TraspasoEntity> obtenerListaDeTraspasos(){
        return (List<TraspasoEntity>) this.traspasoRepository.findAll();
    }

    @CrossOrigin
    @RequestMapping(value = "/traspaso/registrar", method = RequestMethod.POST)
    public boolean registrarTraspaso(@RequestParam("marca") String marca,
                                     @RequestParam("modelo") String modelo){

        SmartphoneEntity entity =
                this.smartphoneRepository.findByModeloContainsAndMarcaContaining(modelo, marca);
        if(entity!=null) {
            entity.setCantidad(entity.getCantidad()-1);
            this.smartphoneRepository.save(entity);
            TraspasoEntity traspasoEntity = new TraspasoEntity();
            traspasoEntity.setFecha(new Date(new GregorianCalendar().getTimeInMillis()));
            traspasoEntity.setTipo("Enviado");
            traspasoEntity.setSmartphone(entity);
            this.traspasoRepository.save(traspasoEntity);
            return true;
        }else {
            return false;
        }
    }
}

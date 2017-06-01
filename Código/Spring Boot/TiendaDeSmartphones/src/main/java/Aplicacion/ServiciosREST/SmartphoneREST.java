package Aplicacion.ServiciosREST;

import Aplicacion.Modelos.SmartphoneEntity;
import Aplicacion.Repository.SmartphoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Creado por Mauricio el 27/04/2017.
 **/
@RestController
public class SmartphoneREST {
    @Autowired
    private SmartphoneRepository smartphoneRepository;

    @CrossOrigin
    @RequestMapping(value = "/smartphone/lista", method = RequestMethod.GET)
    public ArrayList<SmartphoneEntity> obtenerListaDeSmartphone(){
        return (ArrayList<SmartphoneEntity>) this.smartphoneRepository.findAll();
    }

    @CrossOrigin
    @RequestMapping(value = "/smartphone/id/{idSmartphone}", method = RequestMethod.GET)
    public SmartphoneEntity buscarSmartPhonePorID(@PathVariable("idSmartphone") Integer idSmartphone){
        return this.smartphoneRepository.findByIdSmartphone(idSmartphone);
    }

    @RequestMapping(value = "/smartphone/guardar", method = RequestMethod.POST)
    public boolean guardarSmartphone(@RequestBody  SmartphoneEntity smartphoneEntity){
        this.smartphoneRepository.save(smartphoneEntity);
        return true;
    }

    @RequestMapping(value = "/smartphone/eliminar", method = RequestMethod.POST)
    public boolean crearUsuario(@RequestParam("id")Integer id){
        this.smartphoneRepository.delete(id);
        return true;
    }
}

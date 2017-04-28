package Aplicacion.ServiciosREST;

import Aplicacion.Modelos.SmartphoneEntity;
import Aplicacion.Repository.SmartphoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Creado por Mauricio el 27/04/2017.
 **/
@RestController
public class SmartphoneREST {
    @Autowired
    private SmartphoneRepository smartphoneRepository;

    @RequestMapping(value = "/smartphone/lista", method = RequestMethod.GET)
    public ArrayList<SmartphoneEntity> obtenerListaDeSmartphone(){
        return (ArrayList<SmartphoneEntity>) this.smartphoneRepository.findAll();
    }

    @RequestMapping(value = "/smartphone/id/{idSmartphone}", method = RequestMethod.GET)
    public SmartphoneEntity buscarSmartPhonePorID(@PathVariable("idSmartphone") Integer idSmartphone){
        return this.smartphoneRepository.findByIdSmartphone(idSmartphone);
    }
}

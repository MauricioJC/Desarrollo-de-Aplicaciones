package Aplicacion.ServiciosREST;

import Aplicacion.Modelos.SmartphoneEntity;
import Aplicacion.Modelos.UsuarioEntity;
import Aplicacion.Modelos.VentaEntity;
import Aplicacion.Repository.SmartphoneRepository;
import Aplicacion.Repository.UsuarioRepository;
import Aplicacion.Repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Creado por Mauricio el 04/06/2017.
 **/
@RestController
public class VentaREST {
    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private SmartphoneRepository smartphoneRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @RequestMapping(value = "/venta/lista", method = RequestMethod.GET)
    public List<VentaEntity> obtenerListaDeVentas(){
        return (List<VentaEntity>) this.ventaRepository.findAll();
    }

    @RequestMapping(value = "/venta/registrar", method = RequestMethod.POST)
    public void registrarVenta(@RequestParam("idSmartphone") Integer idSmartphone,
                               @RequestParam(value = "idUsuario", defaultValue = "") Integer idUsuario){
        SmartphoneEntity smartphoneEntity = this.smartphoneRepository.findOne(idSmartphone);
        smartphoneEntity.setCantidad(smartphoneEntity.getCantidad()-1);
        this.smartphoneRepository.save(smartphoneEntity);

        UsuarioEntity usuarioEntity = null;
        if (idUsuario != null){
            usuarioEntity = this.usuarioRepository.findOne(idUsuario);
        }

        VentaEntity ventaEntity = new VentaEntity(smartphoneEntity,
                 usuarioEntity,
                 new Date(new GregorianCalendar().getTimeInMillis()));
        this.ventaRepository.save(ventaEntity);
    }
}

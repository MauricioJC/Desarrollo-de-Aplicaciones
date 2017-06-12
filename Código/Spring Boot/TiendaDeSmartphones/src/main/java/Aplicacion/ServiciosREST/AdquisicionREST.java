package Aplicacion.ServiciosREST;

import Aplicacion.Modelos.AdquisicionEntity;
import Aplicacion.Repository.AdquisicionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Creado por Mauricio el 10/06/2017.
 **/
@RestController
public class AdquisicionREST {
    @Autowired
    private AdquisicionRepository repository;


    @RequestMapping(value = "/adquisicion/lista", method = RequestMethod.GET)
    public ArrayList<AdquisicionEntity> obtenerListaDeAdquisiciones(){
        return (ArrayList<AdquisicionEntity>) this.repository.findAll();
    }

    @RequestMapping(value = "/adquisicion/guardar", method = RequestMethod.POST)
    public boolean guardarAdquisicion(@RequestParam("producto")String producto,
                                      @RequestParam("proveedor")String proveedor,
                                      @RequestParam("precio")Integer precio,
                                      @RequestParam("cantidad")Integer cantidad){

        AdquisicionEntity entity = new AdquisicionEntity();
        entity.setCantidad(cantidad);
        entity.setPrecio(precio);
        entity.setProducto(producto);
        entity.setProveedor(proveedor);
        entity.setFecha(new Date(new GregorianCalendar().getTimeInMillis()));
        this.repository.save(entity);
        return true;
    }

    @RequestMapping(value = "/adquisicion/eliminar", method = RequestMethod.POST)
    public boolean eliminarAdquisicion(@RequestParam("id")Integer id){
        this.repository.delete(id);
        return true;
    }

}

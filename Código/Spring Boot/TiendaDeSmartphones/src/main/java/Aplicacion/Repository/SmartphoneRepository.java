package Aplicacion.Repository;

import Aplicacion.Modelos.SmartphoneEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Creado por Mauricio el 27/04/2017.
 **/

public interface SmartphoneRepository extends CrudRepository<SmartphoneEntity,Integer>{
    public SmartphoneEntity findByIdSmartphone(Integer idSmartphone);
}

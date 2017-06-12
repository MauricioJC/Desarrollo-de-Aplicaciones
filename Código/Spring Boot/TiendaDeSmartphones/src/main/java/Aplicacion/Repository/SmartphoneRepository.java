package Aplicacion.Repository;

import Aplicacion.Modelos.SmartphoneEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Creado por Mauricio el 27/04/2017.
 **/

public interface SmartphoneRepository extends CrudRepository<SmartphoneEntity,Integer>{
    public SmartphoneEntity findByIdSmartphone(Integer idSmartphone);
    public List<SmartphoneEntity> findByMarcaContains(String marca);
    public List<SmartphoneEntity> findByModeloContains(String modelo);
    public SmartphoneEntity findByModeloContainsAndMarcaContaining(String modelo, String marca);
}

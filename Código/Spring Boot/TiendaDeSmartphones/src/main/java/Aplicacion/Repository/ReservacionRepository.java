package Aplicacion.Repository;

import Aplicacion.Modelos.ReservacionEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Creado por Mauricio el 05/06/2017.
 **/

public interface ReservacionRepository extends CrudRepository<ReservacionEntity, Integer> {
}

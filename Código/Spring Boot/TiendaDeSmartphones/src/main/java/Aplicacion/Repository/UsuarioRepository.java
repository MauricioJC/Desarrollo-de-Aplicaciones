package Aplicacion.Repository;

import Aplicacion.Modelos.UsuarioEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Mauricio on 26/04/2017.
 */
public interface UsuarioRepository extends CrudRepository<UsuarioEntity,Integer>{
    public UsuarioEntity findByCorreoAndContraseña(String correo, String contraseña);
    public boolean findByCorreoAndIdUsuario(String correo, Integer idUsuario);
}

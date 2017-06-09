package Aplicacion.ServiciosREST;

import Aplicacion.Modelos.SmartphoneEntity;
import Aplicacion.Modelos.UsuarioEntity;
import Aplicacion.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Creado por Mauricio el 27/04/2017.
 **/

@RestController()
public class UsuarioREST {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @CrossOrigin
    @RequestMapping(value = "/usuario/crear", method = RequestMethod.POST)
    public boolean crearUsuario(@RequestParam("correo")String correo, @RequestParam("contraseña")String contraseña){
        usuarioRepository.save(new UsuarioEntity(correo,contraseña));
        return true;
    }

    @CrossOrigin
    @RequestMapping(value = "/usuario/validar", method = RequestMethod.POST)
    public Integer validarUsuario(@RequestParam("correo")String correo, @RequestParam("contraseña")String contraseña){
        UsuarioEntity usuarioEntity = this.usuarioRepository.findByCorreoAndContraseña(correo,contraseña);
        if (usuarioEntity==null){
            return -1;
        }else {
            return usuarioEntity.getIdUsuario();
        }
    }

    //Este acepta JSON
    @CrossOrigin
    @RequestMapping(value = "/usuario/guardar", method = RequestMethod.POST)
    public boolean guardarUsuario(@RequestBody UsuarioEntity usuarioEntity){
        this.usuarioRepository.save(usuarioEntity);
        return true;
    }
}

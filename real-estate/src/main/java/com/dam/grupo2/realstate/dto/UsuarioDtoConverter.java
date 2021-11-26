package com.dam.grupo2.realstate.dto;

import com.dam.grupo2.realstate.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDtoConverter {

    public Usuario createUsuarioDtoToUsuario(CreateUsuarioDto p){
        return new Usuario(
                p.getNombre(),
                p.getApellidos(),
                p.getDireccion(),
                p.getEmail(),
                p.getTelefono(),
                p.getAvatar()

        );
    }

    public GetUsuarioDto usuarioToGetUsuarioDto(Usuario p){
        GetUsuarioDto result = new GetUsuarioDto();
        result.setId(p.getId());
        result.setNombre(p.getNombre());
        result.setApellidos(p.getApellidos());
        result.setDireccion(p.getDireccion());
        result.setEmail(p.getEmail());
        result.setTelefono(p.getTelefono());
        result.setAvatar(p.getAvatar());

        return result;
    }

}

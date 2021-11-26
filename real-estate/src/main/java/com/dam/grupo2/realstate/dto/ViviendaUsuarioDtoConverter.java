package com.dam.grupo2.realstate.dto;


import com.dam.grupo2.realstate.model.Usuario;
import com.dam.grupo2.realstate.model.Vivienda;
import org.springframework.stereotype.Component;

@Component
public class ViviendaUsuarioDtoConverter {


    public Vivienda CreateViviendaUsuarioDtoToVivienda(CreateViviendaUsuarioDto cvpd){
        Vivienda viv = new Vivienda(
                cvpd.getTitulo(),
                cvpd.getDescripcion(),
                cvpd.getAvatar(),
                cvpd.getLating(),
                cvpd.getDireccion(),
                cvpd.getCodigoPostal(),
                cvpd.getPoblacion(),
                cvpd.getProvincia(),
                cvpd.getTipo(),
                cvpd.getPrecio(),
                cvpd.getNumHabitaciones(),
                cvpd.getMetrosCuadrados(),
                cvpd.getNumBanios(),
                cvpd.isTienePiscina(),
                cvpd.isTieneAscensor(),
                cvpd.isTieneGaraje()

        );

        return viv;
    }

    public Usuario CreateViviendaUsuarioDtoToUsuario(CreateViviendaUsuarioDto cvpd){
        Usuario prop = new Usuario(cvpd.getNombre(),cvpd.getApellidos(),
                cvpd.getDireccionProp(),cvpd.getEmail(),cvpd.getTelefono(),
                cvpd.getAvatarProp());

        return prop;
    }

}

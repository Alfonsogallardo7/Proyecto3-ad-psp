package com.dam.grupo2.realstate.dto;


import com.dam.grupo2.realstate.model.Inmobiliaria;
import com.dam.grupo2.realstate.model.Vivienda;
import org.springframework.stereotype.Component;

@Component
public class ViviendaInmobiliariaDtoConverter {
    public Vivienda CreateViviendaInmobiliariaDtoToVivienda(CreateViviendaInmobiliariaDto cvid){
        Vivienda v = new Vivienda(
                cvid.getTitulo(),
                cvid.getDescripcion(),
                cvid.getAvatar(),
                cvid.getLating(),
                cvid.getDireccion(),
                cvid.getCodigoPostal(),
                cvid.getPoblacion(),
                cvid.getProvincia(),
                cvid.getTipo(),
                cvid.getPrecio(),
                cvid.getNumHabitaciones(),
                cvid.getMetrosCuadrados(),
                cvid.getNumBanios(),
                cvid.isTienePiscina(),
                cvid.isTieneAscensor(),
                cvid.isTieneGaraje()
        );

        return v;
    }

    public Inmobiliaria CreateViviendaInmobiliariaDtoToInmobiliaria(CreateViviendaInmobiliariaDto cvid){
        Inmobiliaria inmo = new Inmobiliaria(cvid.getIdInmobiliaria(),cvid.getNombreInmo(),cvid.getEmail(), cvid.getTelefono());
        return inmo;
    }

    public CreateViviendaInmobiliariaDto viviendaInmoToGetViviendaInmoDto(Vivienda v, Inmobiliaria i){
        CreateViviendaInmobiliariaDto result = new CreateViviendaInmobiliariaDto();
        result.setIdVivienda(v.getId());
        result.setTitulo(v.getTitulo());
        result.setDescripcion(v.getDescripcion());
        result.setAvatar(v.getAvatar());
        result.setLating(v.getLating());
        result.setDireccion(v.getDireccion());
        result.setCodigoPostal(v.getCodigoPostal());
        result.setPoblacion(v.getPoblacion());
        result.setProvincia(v.getProvincia());
        result.setTipo(v.getTipo());
        result.setPrecio(v.getPrecio());
        result.setNumHabitaciones(v.getNumHabitaciones());
        result.setMetrosCuadrados(v.getMetrosCuadrados());
        result.setNumBanios(v.getNumBanios());
        result.setTienePiscina((v.isTienePiscina()));
        result.setTieneAscensor(v.isTieneAscensor());
        result.setTieneGaraje(v.isTieneGaraje());
        result.setIdInmobiliaria(i.getId());
        result.setNombreInmo(i.getNombre());
        result.setEmail(i.getEmail());

        return result;
    }

}

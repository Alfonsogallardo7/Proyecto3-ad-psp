package com.dam.grupo2.realstate.dto;

import com.dam.grupo2.realstate.model.Inmobiliaria;
import org.springframework.stereotype.Component;

@Component
public class InmobiliariaDtoConverter {
    public Inmobiliaria createInmobiliariaDtoToInmobiliaria(CreateInmobiliariaDto c) {
        return new Inmobiliaria(
                c.getId(),
                c.getNombre(),
                c.getEmail(),
                c.getTelefono()
        );
    }
    public GetInmobiliariaDto inmobiliariaToGetInmobiliariaDto(Inmobiliaria i) {
        return GetInmobiliariaDto
                .builder()
                .id(i.getId())
                .nombre(i.getNombre())
                .telefono(i.getTelefono())
                .email(i.getEmail())
                .build();
    }
}


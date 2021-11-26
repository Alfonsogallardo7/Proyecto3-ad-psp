package com.dam.grupo2.realstate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class GetUsuarioDto {

    private Long id;
    private String nombre, apellidos, direccion, email, telefono, avatar;
}

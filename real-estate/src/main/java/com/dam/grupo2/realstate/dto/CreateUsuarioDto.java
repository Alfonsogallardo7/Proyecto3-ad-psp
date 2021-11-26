package com.dam.grupo2.realstate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter

public class CreateUsuarioDto {
    private String nombre, apellidos, direccion, email, telefono, avatar;
    private Long vivienda_id;
}

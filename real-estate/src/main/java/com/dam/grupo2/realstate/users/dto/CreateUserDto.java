package com.dam.grupo2.realstate.users.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserDto {

    private String nombre, apellidos, direccion, email, telefono, password, password2,  avatar, fullName, role;
    private Long vivienda_id;
}

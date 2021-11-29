package com.dam.grupo2.realstate.users.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetUserDto {

    private String nombre, apellidos, direccion, email, telefono, password, avatar, fullName, role;
    private Long vivienda_id;
}

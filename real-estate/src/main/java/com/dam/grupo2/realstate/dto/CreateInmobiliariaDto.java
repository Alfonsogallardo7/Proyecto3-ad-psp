package com.dam.grupo2.realstate.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor@AllArgsConstructor
public class CreateInmobiliariaDto {
    private Long id;
    private String nombre , email, telefono;
}

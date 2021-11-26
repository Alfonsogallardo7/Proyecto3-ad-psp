package com.dam.grupo2.realstate.dto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;



@Setter
@Getter
@Builder
public class EditInmobiliariaDto {
private Long id;
    private String nombre;
    private String email;
    private String telefono;

}

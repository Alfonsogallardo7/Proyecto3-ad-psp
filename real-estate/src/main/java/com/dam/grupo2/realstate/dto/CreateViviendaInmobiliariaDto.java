package com.dam.grupo2.realstate.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter


public class CreateViviendaInmobiliariaDto {

    private Long idVivienda;
    private String titulo;
    private String descripcion;
    private String avatar;
    private String lating;
    private String direccion;
    private String codigoPostal;
    private String poblacion;
    private String provincia;
    private String tipo;
    private double precio;
    private int numHabitaciones;
    private double metrosCuadrados;
    private int numBanios;
    private boolean tienePiscina;
    private boolean tieneAscensor;
    private boolean tieneGaraje;

    private Long idInmobiliaria;
    private String nombreInmo;
    private String email;
    private String telefono;


}

package com.dam.grupo2.realstate.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CreateViviendaUsuarioDto {


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
    private Long idUsuario;
    private String nombre;
    private String apellidos;
    private String direccionProp;
    private String email;
    private String telefono;
    private String avatarProp;
    private String mensaje;


}

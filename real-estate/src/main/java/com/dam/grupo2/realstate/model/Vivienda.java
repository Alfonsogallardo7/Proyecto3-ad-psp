package com.dam.grupo2.realstate.model;


import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Vivienda {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Lob
    private String descripcion;

    @Lob
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

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_inmobiliaria")
    private Inmobiliaria inmobiliaria;

    @Builder.Default
    @OneToMany(mappedBy = "vivienda", cascade = CascadeType.REMOVE)
    private List<Interesa> interesa = new ArrayList<>();

    @CreatedBy
    private UUID createdBy;

    public Vivienda(String titulo, String descripcion, String avatar, String lating, String direccion,
                    String codigoPostal, String poblacion, String provincia, String tipo, double precio,
                    int numHabitaciones, double metrosCuadrados, int numBanios, boolean TienePiscina,
                    boolean tieneAscensor, boolean tieneGaraje) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.avatar = avatar;
        this.lating = lating;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.tipo = tipo;
        this.precio = precio;
        this.numHabitaciones = numHabitaciones;
        this.metrosCuadrados = metrosCuadrados;
        this.numBanios = numBanios;
        this.tienePiscina = TienePiscina;
        this.tieneAscensor = tieneAscensor;
        this.tieneGaraje = tieneGaraje;
    }

    /**
     * Al eliminar un propietario se elimina en cascada las viviendas de
     * dicho propietario
     */

    public void addInmobiliaria(Inmobiliaria inmo) {
        inmobiliaria = inmo;
        if (inmo.getViviendas() == null) {
            inmo.setViviendas(new ArrayList());
            inmo.getViviendas().add(this);
        } else {
            inmo.getViviendas().add(this);
        }
    }

    public void removeInmobiliaria() {
        if (this.inmobiliaria != null)
            this.inmobiliaria.getViviendas().remove(this);
        inmobiliaria = null;
    }

    public void addUsuario(Usuario prop) {
        if (prop instanceof Usuario) {
            this.usuario = (Usuario) prop;
            if (((Usuario) prop).getViviendas() == null) {
                ((Usuario) prop).setViviendas(new ArrayList());
                ((Usuario) prop).getViviendas().add(this);
            } else {
                ((Usuario) prop).getViviendas().add(this);
            }
        }
    }

    public void removeUsuario() {
        if (this.usuario != null)
            this.usuario.getViviendas().remove(this);
        this.usuario = null;
    }



}

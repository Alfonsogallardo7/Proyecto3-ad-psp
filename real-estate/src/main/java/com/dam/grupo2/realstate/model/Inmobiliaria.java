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
public class Inmobiliaria {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private String email;
    private String telefono;

    @Builder.Default
    @OneToMany(mappedBy = "inmobiliaria")
    private List<Vivienda> viviendas = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "inmobiliaria")
    private List<Usuario> usuarios =new ArrayList<>();

    @CreatedBy
    private UUID createdBy;

    public Inmobiliaria(Long id , String nombre, String email, String telefono) {
        this.id=id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }
    @PreRemove
    private void removeInmoFromViviendas() {
        viviendas.forEach(v ->
       v.setInmobiliaria(null));}
    }



package com.dam.grupo2.realstate.model;


import com.dam.grupo2.realstate.users.model.UserRole;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@SuperBuilder @AllArgsConstructor @NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)

public class Usuario {

    @Id
    @GeneratedValue
    private long id;

    private String nombre;

    private String apellidos;

    private String direccion;

    private String email;

    private String telefono;

    @Lob
    private String avatar;

    private String password;

    private UserRole role;

    @Builder.Default
    @OneToMany(mappedBy = "usuario")
    private List<Interesa> interesa = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "inmobiliaria", foreignKey = @ForeignKey(name = "FK_VIVIENDA_INMOBILIARIA"))
    private Inmobiliaria inmobiliaria;

    @Builder.Default
    @OneToMany(mappedBy = "usuario")
    List<Vivienda> viviendas = new ArrayList<>();

    @CreatedBy
    private UUID createdBy;


    public Usuario(String nombre, String apellidos, String direccion, String email, String telefono, String avatar) {
    }

    @PreRemove
    private void removeUsuarioFromVivienda(){
        viviendas.forEach(v -> v.setUsuario(null));
    }

}

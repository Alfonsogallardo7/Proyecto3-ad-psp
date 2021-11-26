package com.dam.grupo2.realstate.model;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity @Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
public class Interesa implements Serializable {

    @Builder.Default
    @EmbeddedId
    private InteresaPk id = new InteresaPk();

    @ManyToOne
    @MapsId("vivienda_id")
    @JoinColumn(name = "vivienda_id")
    private Vivienda vivienda;

    @ManyToOne
    @MapsId("usuario_id")
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private LocalDate createdDate;

    @Lob
    private String mensaje;

    @CreatedBy
    private UUID createdBy;

    public Interesa( String mensaje , LocalDate createdDate) {
        this.mensaje = mensaje;
        this.createdDate = createdDate;
    }

    //Helpers

    public void addToVivienda(Vivienda v) {
        vivienda = v;
        v.getInteresa().add(this);
    }

    public void removeFromVivienda(Vivienda v) {
        v.getInteresa().remove(this);
        vivienda = null;
    }

    public void addToUsuario(Usuario usuario) {
        usuario = usuario;
        usuario.getInteresa().add(this);
    }

    public void removeFromUsuario(Usuario usuario) {
        usuario.getInteresa().remove(this);
        usuario = null;
    }

    public void addInteresadoVivienda(Usuario usuario, Vivienda vivienda) {
        addToVivienda(vivienda);
        addToUsuario((Usuario) usuario);
    }

    public void removeInteresadoVivienda(Usuario usuario, Vivienda vivienda) {
        removeFromVivienda(vivienda);
        removeFromUsuario((Usuario) usuario);
    }


}

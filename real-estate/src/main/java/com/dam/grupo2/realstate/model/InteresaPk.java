package com.dam.grupo2.realstate.model;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Lob;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class InteresaPk implements Serializable {

    private Long vivienda_id;

    private Long usuario_id;

}

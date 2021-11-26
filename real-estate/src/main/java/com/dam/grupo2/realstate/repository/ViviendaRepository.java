package com.dam.grupo2.realstate.repository;

import com.dam.grupo2.realstate.model.Vivienda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViviendaRepository
        extends JpaRepository<Vivienda,Long> {
}

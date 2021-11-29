package com.dam.grupo2.realstate.repository;

import com.dam.grupo2.realstate.dto.GetViviendaPropietarioDto;
import com.dam.grupo2.realstate.model.Vivienda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ViviendaRepository
        extends JpaRepository<Vivienda,Long> {

    Page <Vivienda> findAll(Specification<Vivienda> todos, Pageable pageable);

    @Query("""
            select new com.dam.grupo2.realstate.dto.vivienda.GetViviendaPropietarioDto(
            viv.id, viv.titulo, viv.avatar, viv.codigoPostal, viv.provincia, viv.tipo, viv.precio, viv.numHabitaciones, viv.metrosCuadrados,
            viv.numBanios, viv.tienePiscina, viv.tieneAscensor, viv.tieneGaraje
            )
            from Vivienda viv
            where viv.usuario.id = :id
            """)
    List<GetViviendaPropietarioDto> findByUsuario(UUID id);
}

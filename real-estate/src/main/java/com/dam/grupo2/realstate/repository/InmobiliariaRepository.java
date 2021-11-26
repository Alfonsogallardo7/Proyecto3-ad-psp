package com.dam.grupo2.realstate.repository;

import com.dam.grupo2.realstate.model.Inmobiliaria;
import com.dam.grupo2.realstate.users.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InmobiliariaRepository
        extends JpaRepository<Inmobiliaria, Long> {

    //Page<Inmobiliaria> findByCliente (UserEntity cliente, Pageable pageable);
    //Page<Inmobiliaria> findByAutenticado(UserEntity cliente, Pageable pageable);

}

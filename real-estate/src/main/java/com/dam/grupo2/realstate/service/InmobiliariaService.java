package com.dam.grupo2.realstate.service;

import com.dam.grupo2.realstate.model.Inmobiliaria;
import com.dam.grupo2.realstate.repository.InmobiliariaRepository;
import com.dam.grupo2.realstate.service.base.BaseService;
import com.dam.grupo2.realstate.users.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InmobiliariaService
        extends BaseService<Inmobiliaria,Long, InmobiliariaRepository> {
/*
    public Page<Inmobiliaria> findAllByGestor (UserEntity user, Pageable pageable) {
        return repositorio.findByCliente(user, pageable);
    }

    public Page<Inmobiliaria> findAllByAuthenticado (UserEntity user, Pageable pageable) {
        return repositorio.findByAutenticado(user,pageable);
    }
*/


}

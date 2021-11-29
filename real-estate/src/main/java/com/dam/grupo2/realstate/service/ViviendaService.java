package com.dam.grupo2.realstate.service;

import com.dam.grupo2.realstate.dto.GetViviendaDto;
import com.dam.grupo2.realstate.dto.GetViviendaPropietarioDto;
import com.dam.grupo2.realstate.model.Vivienda;
import com.dam.grupo2.realstate.repository.ViviendaRepository;
import com.dam.grupo2.realstate.service.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ViviendaService
        extends BaseService<Vivienda,Long,ViviendaRepository> {

    public List<GetViviendaPropietarioDto> findByPropietario (UUID id) {
        return repositorio.findByUsuario(id);
    }

}

package com.dam.grupo2.realstate.service;

import com.dam.grupo2.realstate.model.Interesa;
import com.dam.grupo2.realstate.repository.InteresaRepository;
import com.dam.grupo2.realstate.service.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class InteresaService
        extends BaseService<Interesa, Long , InteresaRepository> {
}

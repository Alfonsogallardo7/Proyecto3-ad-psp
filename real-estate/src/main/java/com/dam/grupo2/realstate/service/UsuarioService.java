package com.dam.grupo2.realstate.service;

import com.dam.grupo2.realstate.model.Usuario;
import com.dam.grupo2.realstate.repository.UsuarioRepository;
import com.dam.grupo2.realstate.service.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService
        extends BaseService<Usuario,Long, UsuarioRepository> {


}

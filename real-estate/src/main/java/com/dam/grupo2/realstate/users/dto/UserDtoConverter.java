package com.dam.grupo2.realstate.users.dto;

import com.dam.grupo2.realstate.users.model.UserEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserDtoConverter {

    public GetUserDto convertUserEntityToGetUserDto(UserEntity user) {
        return GetUserDto.builder()
                .nombre(user.getNombre())
                .apellidos(user.getApellidos())
                .avatar(user.getAvatar())
                .direccion(user.getDireccion())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();


    }
}

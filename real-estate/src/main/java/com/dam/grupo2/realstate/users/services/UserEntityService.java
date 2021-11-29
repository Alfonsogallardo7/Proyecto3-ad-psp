package com.dam.grupo2.realstate.users.services;

import com.dam.grupo2.realstate.service.base.BaseService;
import com.dam.grupo2.realstate.users.dto.CreateUserDto;
import com.dam.grupo2.realstate.users.model.UserEntity;
import com.dam.grupo2.realstate.users.model.UserRole;
import com.dam.grupo2.realstate.users.repository.UserEntityRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("userDetailsService")
@RequiredArgsConstructor
public class UserEntityService extends BaseService<UserEntity, UUID, UserEntityRepository> implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.repositorio.findFirstByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(email + " no encontrado"));
    }


    public UserEntity save(CreateUserDto newUser) {
        if (newUser.getPassword().contentEquals(newUser.getPassword2())) {

            UserEntity userEntity = UserEntity.builder()
                    .password(passwordEncoder.encode(newUser.getPassword()))
                    .avatar(newUser.getAvatar())
                    .fullName(newUser.getFullName())
                    .email(newUser.getEmail())
                    .role(UserRole.valueOf(newUser.getRole()))
                    .build();
            return save(userEntity);
        } else {
            return null;
        }
    }
}

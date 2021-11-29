package com.dam.grupo2.realstate.users.model;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import javax.persistence.*;

import com.dam.grupo2.realstate.model.Inmobiliaria;
import com.dam.grupo2.realstate.model.Interesa;
import com.dam.grupo2.realstate.model.Vivienda;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String nombre;

    private String apellidos;

    private String direccion;

    @NaturalId
    @Column(unique = true, updatable = false)
    private String email;

    private String telefono;

    private String password;

    private String avatar;

    private String fullName;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Builder.Default
    @OneToMany(mappedBy = "usuario")
    private List<Interesa> interesa = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "inmobiliaria", foreignKey = @ForeignKey(name = "FK_VIVIENDA_INMOBILIARIA"))
    private Inmobiliaria inmobiliaria;

    @Builder.Default
    @OneToMany(mappedBy = "usuario")
    List<Vivienda> viviendas = new ArrayList<>();

    public UserEntity(String nombre, String apellidos, String email, String telefono, String password, String avatar, String fullName, UserRole role, Inmobiliaria inmobiliaria, LocalDateTime lastPasswordChangeAt) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
        this.password = password;
        this.avatar = avatar;
        this.fullName = fullName;
        this.role = role;
        this.inmobiliaria = inmobiliaria;
        this.lastPasswordChangeAt = lastPasswordChangeAt;
    }

    public UserEntity(String nombre, String apellidos, String email, String telefono, String password, String avatar, String fullName, UserRole role, List<Interesa> interesa, Inmobiliaria inmobiliaria, List<Vivienda> viviendas, LocalDateTime createdAt) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
        this.password = password;
        this.avatar = avatar;
        this.fullName = fullName;
        this.role = role;
        this.interesa = interesa;
        this.inmobiliaria = inmobiliaria;
        this.viviendas = viviendas;
        this.createdAt = createdAt;
    }

    @PreRemove
    private void removeUsuarioFromVivienda(){
        viviendas.forEach(v -> v.setUsuario(null));
    }


    @CreatedDate
    private LocalDateTime createdAt;

    @Builder.Default
    private LocalDateTime lastPasswordChangeAt = LocalDateTime.now();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return true;
    }


}

package com.dam.grupo2.realstate;

import com.dam.grupo2.realstate.model.Inmobiliaria;
import com.dam.grupo2.realstate.model.Interesa;
import com.dam.grupo2.realstate.model.Usuario;
import com.dam.grupo2.realstate.model.Vivienda;
import com.dam.grupo2.realstate.service.InmobiliariaService;
import com.dam.grupo2.realstate.service.InteresaService;
import com.dam.grupo2.realstate.service.UsuarioService;
import com.dam.grupo2.realstate.service.ViviendaService;
import com.dam.grupo2.realstate.users.model.UserEntity;
import com.dam.grupo2.realstate.users.model.UserRole;
import com.dam.grupo2.realstate.users.services.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class MainDePrueba {

    private final ViviendaService viviendaService;
    private final InteresaService interesaService;
    private final InmobiliariaService inmobiliariaService;
    private final UserEntityService userEntityService;


    @PostConstruct
    public void DatosDePrueba() {

        Vivienda vivienda1 = Vivienda.builder()
                .titulo("Casa1")
                .descripcion("Una casa cualquiera")
                .avatar("avatar.com.org")
                .lating("45,34")
                .direccion("Calle calle1")
                .codigoPostal("41010")
                .poblacion("Sevilla")
                .provincia("Sevilla")
                .tipo("Casa")
                .precio(300000.00)
                .numHabitaciones(5)
                .metrosCuadrados(70)
                .numBanios(2)
                .tienePiscina(true)
                .tieneGaraje(true)
                .tieneAscensor(false)
                .build();

        Vivienda vivienda2 = Vivienda.builder()
                .titulo("Casa1")
                .descripcion("Una casa cualquiera")
                .avatar("avatar.com.org")
                .lating("45,34")
                .direccion("Calle calle1")
                .codigoPostal("41010")
                .poblacion("Sevilla")
                .provincia("Sevilla")
                .tipo("Casa")
                .precio(300000.00)
                .numHabitaciones(5)
                .metrosCuadrados(70)
                .numBanios(2)
                .tienePiscina(true)
                .tieneGaraje(true)
                .tieneAscensor(false)
                .build();

        viviendaService.save(vivienda1);
        viviendaService.save(vivienda2);


        Inmobiliaria inmo1 = Inmobiliaria.builder()
                .nombre("inmo1")
                .email("elemail1@gmail.com")
                .telefono("656565665")
                .build();

        Inmobiliaria inmo2 = Inmobiliaria.builder()
                .nombre("inmo2")
                .email("elemail1@gmail.com")
                .telefono("656565665")
                .build();

        vivienda1.addInmobiliaria(inmo1);
        vivienda2.addInmobiliaria(inmo1);

        inmobiliariaService.save(inmo1);
        inmobiliariaService.save(inmo2);


        Usuario int1 = Usuario.builder()
                .nombre("Daniel")
                .apellidos("de Luna")
                .direccion("Calle conde del bustillo")
                .email("deluna.rodan21@triana.salesianos.edu")
                .telefono("674838160")
                .avatar("http://avatar.jpg")
                .role(UserRole.PROPIETARIO)
                .build();

        userEntityService.save(int1);

        Interesa interesa = Interesa.builder()
                .usuario(int1)
                .vivienda(vivienda1)
                .mensaje("Estoy interesado")
                .createdDate(LocalDate.now())
                .build();
        interesaService.save(interesa);


        interesa.addInteresadoVivienda(int1, vivienda1);

        viviendaService.save(vivienda1);
        viviendaService.save(vivienda2);


        UserEntity usuario1 = UserEntity.builder()
                .nombre("Alfonso")
                .apellidos("Gallardo Rodríguez")
                .direccion("Canarias 114")
                .email("alfonsogallardo@gmail.com")
                .telefono("666777999")
                .avatar("URL")
                .role(UserRole.GESTOR)
                .build();


        vivienda1.addUsuario(usuario1);
        vivienda2.addUsuario(usuario1);

        Service.save(usuario1);

        viviendaService.save(vivienda1);
        viviendaService.save(vivienda2);

        Usuario propietario2 = Usuario.builder()
                .nombre("Manuel")
                .apellidos("Espinola Rodríguez")
                .direccion("Canarias 114")
                .email("alfonsogallardo@gmail.com")
                .telefono("666777999")
                .avatar("URL")
                .role(UserRole.ADMIN)
                .build();

        usuarioService.save(usuario1);
        usuarioService.save(propietario2);


    }
}

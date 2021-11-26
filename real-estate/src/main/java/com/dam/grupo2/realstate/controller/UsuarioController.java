package com.dam.grupo2.realstate.controller;

import com.dam.grupo2.realstate.dto.GetUsuarioDto;
import com.dam.grupo2.realstate.dto.UsuarioDtoConverter;
import com.dam.grupo2.realstate.model.Usuario;
import com.dam.grupo2.realstate.service.UsuarioService;
import com.dam.grupo2.realstate.users.model.UserEntity;
import com.dam.grupo2.realstate.users.model.UserRole;
import com.dam.grupo2.realstate.util.PaginationLinksUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@Api(tags = "Los controladores de propietarios")
@RequestMapping("/propietario")
@RequiredArgsConstructor
@CrossOrigin
public class UsuarioController {

    private final UsuarioService services;
    private final UsuarioDtoConverter dtoConverter;
    private final PaginationLinksUtils paginationLinksUtils;

    @ApiOperation(value = "Get", notes = "Devuelve una lista con todos las propietarios registrados.")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Se a encontrado la lista de propietarios y se ha devuelto correctamente."),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "No se ha encontrado la lista de propietarios.") })

    @GetMapping("/")
    public ResponseEntity<Page<GetUsuarioDto>> findAll(@PageableDefault(size=10, page=0) Pageable pageable, HttpServletRequest request ,@AuthenticationPrincipal UserEntity user){
        Page<Usuario> data= services.findAll(pageable);
        if (data.isEmpty()){
            return ResponseEntity.notFound().build();
        }else {
            Page<GetUsuarioDto> result=
                    data.map(dtoConverter::usuarioToGetUsuarioDto);

            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("Link" ,
                    paginationLinksUtils.createLinkHeader(result , uriBuilder)).body(result);
        }
    }


    @ApiOperation(value = "Get", notes = "Devuelve el propietario dependiendo del id que se le haya pasado")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Se a encontrado el propietario"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "No se ha encontrado el propietario") })
    @GetMapping("/{id}")
            public ResponseEntity<GetUsuarioDto> findById(@PathVariable Long id) {
    Optional<Usuario> propietarioBuscado = services.findById(id);

        if(propietarioBuscado.isEmpty())
                return ResponseEntity.notFound().build();
        else
                return ResponseEntity.ok().body(dtoConverter.usuarioToGetUsuarioDto(propietarioBuscado.get()));
}

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        services.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

package com.dam.grupo2.realstate.controller;

import com.dam.grupo2.realstate.dto.*;
import com.dam.grupo2.realstate.error.InmoNotFoundException;
import com.dam.grupo2.realstate.model.Inmobiliaria;
import com.dam.grupo2.realstate.service.InmobiliariaService;
import com.dam.grupo2.realstate.service.ViviendaService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@Api(tags = "Los controladores de Inmobiliaria")
@RequestMapping("/inmobiliaria")
@RequiredArgsConstructor
@CrossOrigin
public class InmobiliariaController {

    private final InmobiliariaService inmoService;
    private final InmobiliariaDtoConverter inmodtoConverter;
    private final ViviendaService viviendaService;
    private final PaginationLinksUtils paginationLinksUtils;

    @ApiOperation(value = "Get", notes = "Devuelve una lista con todos las inmobiliarias registradas.")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Se a encontrado la lista de inmobiliarias y se ha devuelto correctamente."),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "No se ha encontrado la lista de inmobiliarias.") })
    @GetMapping("/")
    public ResponseEntity<Page<GetInmobiliariaDto>> findAllByUserAutenticated (@PageableDefault(size=10, page=0) Pageable pageable, HttpServletRequest request, @AuthenticationPrincipal UserEntity user){
        Page<Inmobiliaria> data= inmoService.findAll(pageable);
        if (user.getRole().equals(UserRole.ADMIN)||user.getRole().equals(UserRole.PROPIETARIO)||user.getRole().equals(UserRole.GESTOR)) {
            data = inmoService.findAll(pageable);
        }else {
            return ResponseEntity.status(403).build();
        }
        if (data.isEmpty()){
            return ResponseEntity.notFound().build();
        }else {
            Page<GetInmobiliariaDto> result=
                    data.map(inmodtoConverter::inmobiliariaToGetInmobiliariaDto);

            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("Link" ,
                    paginationLinksUtils.createLinkHeader(result , uriBuilder)).body(result);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetInmobiliariaDto> findById(@PathVariable Long id, @AuthenticationPrincipal UserEntity user){

        Optional<Inmobiliaria> inmoBuscada = inmoService.findById(id);
        if(inmoBuscada.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok().body(inmodtoConverter.inmobiliariaToGetInmobiliariaDto(inmoBuscada.get()));
    }

    @ApiOperation(value = "Post", notes = "Crea una nueva Inmobiliaria")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Se ha añadido la Inmobiliaria"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "No se ha podido añadir la inmobiliaria, algún errror en los datos") })
    @PostMapping("/")
    public ResponseEntity<Inmobiliaria> create(@RequestBody Inmobiliaria nuevaInmo){
        if (nuevaInmo.getNombre() == null){
            return ResponseEntity.badRequest().build();
        }else {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(inmoService.save(nuevaInmo));
        }
    }
    @ApiOperation(value = "Delete", notes = "Borra la inmobiliaria seleccionada")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "Se ha borrado correctamente")})

    @DeleteMapping("/{id}")  // TODO Esta petición debería ser idempotente y la primera vez devuelve 204 y la segunda 404
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if(inmoService.findById(id).isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }else{
            inmoService.deleteById(id);
            viviendaService.findById(id).map(v ->{
                v.setInmobiliaria(null);
                return viviendaService.save(v);
            });
            return ResponseEntity.noContent().build();
        }
    }
    @ApiOperation(value = "Edit", notes = "Edita la inmobiliaria seleccionada")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Se ha actualizado con éxito"),
            @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "Se ha actualizado con éxito")
    })
    @PutMapping("{id}")
    public Inmobiliaria editInmo(@RequestBody EditInmobiliariaDto editar, @PathVariable Long id) {

        return inmoService.findById(id).map(i -> {
            i.setNombre(editar.getNombre());
            i.setEmail((editar.getEmail()));
            i.setTelefono(editar.getTelefono());
            return inmoService.save(i);
        }).orElseThrow(() -> new InmoNotFoundException());
    }



}

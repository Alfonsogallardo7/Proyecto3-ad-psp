package com.dam.grupo2.realstate.controller;

import com.dam.grupo2.realstate.dto.*;
import com.dam.grupo2.realstate.model.*;
import com.dam.grupo2.realstate.service.UsuarioService;
import com.dam.grupo2.realstate.service.ViviendaService;
import com.dam.grupo2.realstate.util.PaginationLinksUtils;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


@RestController
@Api(tags = "Los controladores de interesado")
@RequestMapping("/interesado")
@RequiredArgsConstructor
@CrossOrigin
public class InteresadoController {


   private final UsuarioService usuarioService;
   private final ViviendaService viviendaService;
   private final ViviendaDtoConverter dtoConverterviv;
   private final ViviendaUsuarioDtoConverter viviendaInteresadoDtoConverter;
   private final UsuarioDtoConverter dtoConverter;
   private final PaginationLinksUtils paginationLinksUtils;

    @PostMapping("/vivienda/{id}/meinteresa")
    public ResponseEntity<Interesa> create(@PathVariable Long id , @RequestBody CreateViviendaUsuarioDto cvid){
        Optional<Usuario> intAAniadir = usuarioService.findById(cvid.getIdUsuario());
        Optional<Vivienda> v = viviendaService.findById(id);
        Usuario p = (Usuario)viviendaInteresadoDtoConverter.CreateViviendaUsuarioDtoToUsuario(cvid);

         if (v.isEmpty() || intAAniadir.isEmpty()) {
             return ResponseEntity.badRequest().build();

         }else{
             Interesa in = new Interesa(cvid.getMensaje(), LocalDate.now());
             in.addInteresadoVivienda(intAAniadir.get() , v.get());
             usuarioService.save(intAAniadir.get());
             viviendaService.save(v.get());
             GetViviendaDto getVivienda = dtoConverterviv.viviendaToGetViviendaDto(v.get());
             return ResponseEntity.ok().body(in);
         }
    }


    @ApiOperation(value = "Get", notes = "Devuelve una lista con todos los usuarios interesados registrados.")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Se a encontrado la lista de usuarios interesados y se ha devuelto correctamente."),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "No se ha encontrado la lista de interesados.") })
    @GetMapping("/")
    @CrossOrigin
    public ResponseEntity<Page<GetUsuarioDto>> findAll(@PageableDefault(size=10, page=0) Pageable pageable, HttpServletRequest request){
        Page<Usuario> data= usuarioService.findAll(pageable);
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

}

package com.dam.grupo2.realstate.controller;

import com.dam.grupo2.realstate.dto.*;
import com.dam.grupo2.realstate.model.Inmobiliaria;
import com.dam.grupo2.realstate.model.Usuario;
import com.dam.grupo2.realstate.model.Vivienda;
import com.dam.grupo2.realstate.service.InmobiliariaService;
import com.dam.grupo2.realstate.service.UsuarioService;
import com.dam.grupo2.realstate.service.ViviendaService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


@RestController
@Api(tags = "Los controladores de viviendas")
@RequestMapping("/vivienda")
@RequiredArgsConstructor
@CrossOrigin
public class ViviendaController {

    private final ViviendaService vService;
    private final UsuarioService usuarioService;
    private final InmobiliariaService iService;
    private final ViviendaDtoConverter dtoConverter;
    private final ViviendaUsuarioDtoConverter dtoViviendaPropConverter;
    private final InmobiliariaService inmoService;
    private final ViviendaInmobiliariaDtoConverter dtoViviendaInmobiliariaConverter;
    private final PaginationLinksUtils paginationLinksUtils;

    @ApiOperation(value = "Get", notes = "Devuelve una lista con todos las viviendas registradas.")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Se a encontrado la lista de viviendas y se ha devuelto correctamente."),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "No se ha encontrado la lista de viviendas.") })

    @GetMapping("/")
    public ResponseEntity<Page<GetViviendaDto>> findAll(@PageableDefault(size=10, page=0) Pageable pageable, HttpServletRequest request) {
        Page<Vivienda> data = vService.findAll(pageable);
        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            Page<GetViviendaDto> result =
                    data.map(dtoConverter::viviendaToGetViviendaDto);

            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity
                    .ok().header("Link" ,
                            paginationLinksUtils.createLinkHeader(result , uriBuilder))
                    .body(result);
        }
    }

    @ApiOperation(value = "Get", notes = "Devuelve la vivienda dependiendo del id que se le haya pasado")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Se a encontrado la vivienda"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "No se ha encontrado la vivienda") })
    @GetMapping("/{id}")
    public ResponseEntity<GetViviendaDto> findById(@PathVariable Long id){

        Optional<Vivienda> viviendaBuscada = vService.findById(id);

        if(viviendaBuscada.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok().body(dtoConverter.viviendaToGetViviendaDto(viviendaBuscada.get()));
    }

    @ApiOperation(value = "post", notes = "Añade una nueva vivienda y un propietario si no existiese.")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_CREATED, message = "Se ha creado correctamente")})
    @PostMapping("/")


    @CrossOrigin
    public ResponseEntity<GetViviendaDto> create(@RequestBody CreateViviendaUsuarioDto viviendaACrear){

        Vivienda v = dtoViviendaPropConverter.CreateViviendaUsuarioDtoToVivienda(viviendaACrear);

        if (!usuarioService.existsById(viviendaACrear.getIdUsuario())) {
            Usuario p = dtoViviendaPropConverter.CreateViviendaUsuarioDtoToUsuario(viviendaACrear);
            if(viviendaACrear.getIdInmobiliaria()!=null){
                Optional<Inmobiliaria> inmobiliariaAsociada = inmoService.findById(viviendaACrear.getIdInmobiliaria());
                if(inmobiliariaAsociada.isPresent())
                    v.addInmobiliaria(inmobiliariaAsociada.get());
            }
            if(viviendaACrear.getIdUsuario()!=null) {
                Optional<Usuario> propietarioAsociado = usuarioService.findById(viviendaACrear.getIdUsuario());
                if (propietarioAsociado.isPresent())
                    v.addUsuario(p);
            }
            usuarioService.save(p);
            vService.save(v);
        }
        else{
            usuarioService.findById(viviendaACrear.getIdUsuario()).ifPresent(propBuscado ->{
                Optional<Usuario> propietarioAsociado = usuarioService.findById(viviendaACrear.getIdUsuario());
                if(viviendaACrear.getIdInmobiliaria()!=null){
                    Optional<Inmobiliaria> inmobiliariaAsociada = inmoService.findById(viviendaACrear.getIdInmobiliaria());
                    if(inmobiliariaAsociada.isPresent())
                        v.addInmobiliaria(inmobiliariaAsociada.get());
                }
                if(propietarioAsociado.isPresent())
                    v.addUsuario(propBuscado);
                usuarioService.save(propBuscado);
                vService.save(v);
            }
            );

        }

        GetViviendaDto getVivienda = dtoConverter.viviendaToGetViviendaDto(v);

        return ResponseEntity.ok().body(getVivienda);
    }
    @ApiOperation(value = "Put", notes = "Modifica la vivienda seleccionada.")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Se ha modificado correctamente la vivienda."),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "No se ha podido modificar, no existe la vivienda.") })
    @PutMapping("/{id}")
    public ResponseEntity<Vivienda> edit(@RequestBody Vivienda v, @PathVariable Long id){
        return ResponseEntity.of(
                vService.findById(id).map(ev ->{
                    ev.setTitulo(v.getTitulo());
                    ev.setDescripcion(v.getDescripcion());
                    ev.setAvatar(v.getAvatar());
                    ev.setLating(v.getLating());
                    ev.setDireccion(v.getDireccion());
                    ev.setCodigoPostal(v.getCodigoPostal());
                    ev.setPoblacion(v.getPoblacion());
                    ev.setProvincia(v.getProvincia());
                    ev.setTipo(v.getTipo());
                    ev.setPrecio(v.getPrecio());
                    ev.setNumHabitaciones(v.getNumHabitaciones());
                    ev.setMetrosCuadrados(v.getMetrosCuadrados());
                    ev.setNumBanios(v.getNumBanios());
                    ev.setTienePiscina(v.isTienePiscina());
                    ev.setTieneAscensor(v.isTieneAscensor());
                    ev.setTieneGaraje(v.isTieneGaraje());
                    return ev;
                })
        );
    }

    @ApiOperation(value = "Delete", notes = "Borra la vivienda seleccionada")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "Se ha borrado correctamente")})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        Optional<Vivienda> viviendaABorrar = vService.findById(id);

        viviendaABorrar.ifPresent(v -> {
            v.removeInmobiliaria();
            v.removeUsuario();
            vService.deleteById(id);
        });

        return ResponseEntity.noContent().build();


    }

    @PostMapping("/{idVivienda}/inmobiliaria/{idInmobiliaria}")
    public ResponseEntity<CreateViviendaInmobiliariaDto> addInmoToVivienda(@RequestBody CreateViviendaInmobiliariaDto viviendainmo){
        Vivienda v = dtoViviendaInmobiliariaConverter.CreateViviendaInmobiliariaDtoToVivienda(viviendainmo);
        Inmobiliaria inmo = dtoViviendaInmobiliariaConverter.CreateViviendaInmobiliariaDtoToInmobiliaria(viviendainmo);


        if (!iService.existsById(viviendainmo.getIdInmobiliaria())) {
            Inmobiliaria i = dtoViviendaInmobiliariaConverter.CreateViviendaInmobiliariaDtoToInmobiliaria(viviendainmo);
            v.addInmobiliaria(i);
            iService.save(i);
            vService.save(v);
        }
        else{
            iService.findById(viviendainmo.getIdInmobiliaria()).ifPresent(inmoBuscar ->{
                v.addInmobiliaria(inmoBuscar);
                iService.save(inmoBuscar);
                vService.save(v);
            }
            );
        }
        CreateViviendaInmobiliariaDto getViviendaInmobiliaria = dtoViviendaInmobiliariaConverter.viviendaInmoToGetViviendaInmoDto(v, inmo);

        return ResponseEntity.ok().body(getViviendaInmobiliaria);
    }

    @DeleteMapping("{id}/inmobiliaria")
    public ResponseEntity<?> deleteInmobiliaria (@PathVariable Long id){
        Optional <Vivienda> vivienda = vService.findById(id);

        if (vivienda.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            vivienda.map(v -> {
                v.setInmobiliaria(null);
                vService.save(v);
                return ResponseEntity.noContent().build();
            });  return ResponseEntity.noContent().build();

        }
    }




}

package com.cinerikuy.cinema.controller;

import com.cinerikuy.cinema.entity.Cinema;
import com.cinerikuy.cinema.entity.City;
import com.cinerikuy.cinema.exception.AdminException;
import com.cinerikuy.cinema.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /** POR CUESTIONES DE INTEGRIDAD DE LA DATA, EL BORRADO FÍSICO NO ES POSIBLE, SOLO BORRADO LÓGICO */

    /** CINEMA */

    @Tag(name = "admin-cinema")
    @Operation(summary = "Recupera todos los cines.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todos los cines recuperados con éxito", content = @Content),
            @ApiResponse(responseCode = "412", description = "No hay cines en la base de datos", content = @Content)})
    @GetMapping("/cinemas")
    public ResponseEntity<List<Cinema>> cinemaFindAll() throws AdminException {
        List<Cinema> list = adminService.cinemaFindAll();
        if(list == null)
            throw new AdminException("C001", "No hay cines en la base de datos.", HttpStatus.PRECONDITION_FAILED);
        return ResponseEntity.ok(list);
    }

    @Tag(name = "admin-cinema")
    @Operation(summary = "Recupera 1 cine por su 'id'.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cine recuperado con éxito", content = @Content),
            @ApiResponse(responseCode = "412", description = "No existe un cine con ese 'id'", content = @Content)})
    @GetMapping("/cinemas/{id}")
    public ResponseEntity<Cinema> cinemaFindById(@PathVariable long id) throws AdminException {
        Cinema cinema = adminService.cinemaFindById(id);
        if(cinema == null)
            throw new AdminException("C001", "El cine buscado no existe.", HttpStatus.PRECONDITION_FAILED);
        return ResponseEntity.ok(cinema);
    }

    @Tag(name = "admin-cinema")
    @Operation(summary = "Guarda/Actualiza el cine enviado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cine guardado/actualizado con éxito", content = @Content),
            @ApiResponse(responseCode = "412", description = "Error al momento de guardar/actualizar el cine enviado", content = @Content)})
    @PostMapping("/cinemas")
    public ResponseEntity<Cinema> cinemaSaveUpdate(@RequestBody Cinema input) throws AdminException {
        Cinema save;
        try{
            save = adminService.cinemaSaveUpdate(input);
        }catch (Exception e) {
            throw new AdminException("C001", "Error en la data enviada.", HttpStatus.PRECONDITION_FAILED);
        }
        return ResponseEntity.ok(save);
    }

//    @Tag(name = "admin-cinema")
//    @Operation(summary = "Elimina 1 cine por su 'id'.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Cine eliminado con éxito", content = @Content),
//            @ApiResponse(responseCode = "412", description = "No existe un cine con ese 'id'", content = @Content)})
//    @DeleteMapping("/cinemas/{id}")
//    public ResponseEntity<String> cinemaDelete(@PathVariable long id) throws AdminException {
//        Cinema cinema = this.cinemaFindById(id).getBody();
//        adminService.cinemaDelete(id);
//        String message = "";
//        try{
//            this.cinemaFindById(id).getBody();
//        }catch (AdminException e){
//            message = "Cine eliminado con éxito";
//        }finally{
//            return ResponseEntity.ok(message);
//        }
//    }

    /** CITY */

    @Tag(name = "admin-city")
    @Operation(summary = "Recupera todas las ciudades.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todas las ciudades recuperadas con éxito", content = @Content),
            @ApiResponse(responseCode = "412", description = "No hay ciudades en la base de datos", content = @Content)})
    @GetMapping("/cinemas/cities")
    public ResponseEntity<List<City>> cityFindAll() throws AdminException {
        List<City> list = adminService.cityFindAll();
        if(list == null)
            throw new AdminException("C001", "No hay ciudades en la base de datos.", HttpStatus.PRECONDITION_FAILED);
        return ResponseEntity.ok(list);
    }

    @Tag(name = "admin-city")
    @Operation(summary = "Recupera 1 ciudad por su 'id'.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ciudad recuperada con éxito", content = @Content),
            @ApiResponse(responseCode = "412", description = "No existe ciudad con ese 'id'", content = @Content)})
    @GetMapping("/cinemas/cities/{id}")
    public ResponseEntity<City> cityFindById(@PathVariable long id) throws AdminException {
        City city = adminService.cityFindById(id);
        if(city == null)
            throw new AdminException("C001", "La ciudad buscada no existe.", HttpStatus.PRECONDITION_FAILED);
        return ResponseEntity.ok(city);
    }

    @Tag(name = "admin-city")
    @Operation(summary = "Guarda/Actualiza la ciudad enviada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ciudad guardada/actualizada con éxito", content = @Content),
            @ApiResponse(responseCode = "412", description = "Error al momento de guardar/actualizar la ciudad enviada", content = @Content)})
    @PostMapping("/cinemas/cities")
    public ResponseEntity<City> citySaveUpdate(@RequestBody City input) throws AdminException {
        String cityNameSend = input.getCity();
        List<City> cities = this.cityFindAll().getBody();
        List<City> exist = cities.stream()
                .filter(c -> c.getCity().equalsIgnoreCase(cityNameSend)).collect(Collectors.toList());
        if(!exist.isEmpty())
            throw new AdminException("C001", "La ciudad enviada ya está registrada.", HttpStatus.PRECONDITION_FAILED);
        City save = adminService.citySaveUpdate(input);
        return ResponseEntity.ok(save);
    }

}

package com.cinerikuy.customer.controller;

import com.cinerikuy.customer.entity.Customer;
import com.cinerikuy.customer.exception.AdminException;
import com.cinerikuy.customer.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /** POR CUESTIONES DE INTEGRIDAD DE LA DATA, EL BORRADO FÍSICO NO ES POSIBLE, SOLO BORRADO LÓGICO */

    // TODO .. no debe devolver el password

    @Tag(name = "admin-customer")
    @Operation(summary = "Recupera todos los usuarios.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todos los usuarios recuperados con éxito", content = @Content),
            @ApiResponse(responseCode = "412", description = "No hay usuarios en la base de datos", content = @Content)})
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> customerFindAll() throws AdminException {
        List<Customer> list = adminService.customerFindAll();
        if(list == null)
            throw new AdminException("C001", "No hay usuarios en la base de datos.", HttpStatus.PRECONDITION_FAILED);
        return ResponseEntity.ok(list);
    }

    @Tag(name = "admin-customer")
    @Operation(summary = "Recupera 1 usuario por su 'id'.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario recuperado con éxito", content = @Content),
            @ApiResponse(responseCode = "412", description = "No existe usuario con ese 'id'", content = @Content)})
    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> customerFindById(@PathVariable long id) throws AdminException {
        Customer customer = adminService.customerFindById(id);
        if(customer == null)
            throw new AdminException("C001", "El usuario buscado no existe.", HttpStatus.PRECONDITION_FAILED);
        return ResponseEntity.ok(customer);
    }

    // TODO .. el admin solo debería poder guardar/actualizar usuarios con role-admin, no role-customer
    @Tag(name = "admin-customer")
    @Operation(summary = "Guarda/Actualiza el usuario enviado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario guardado/actualizado con éxito", content = @Content),
            @ApiResponse(responseCode = "412", description = "Error al momento de guardar/actualizar el usuario enviado", content = @Content)})
    @PostMapping("/customers")
    public ResponseEntity<Customer> customerSaveUpdate(@RequestBody Customer input) throws AdminException {
        Customer save;
        try{
            save = adminService.customerSaveUpdate(input);
        }catch (Exception e) {
            throw new AdminException("C001", "Error en la data enviada.", HttpStatus.PRECONDITION_FAILED);
        }
        return ResponseEntity.ok(save);
    }

}

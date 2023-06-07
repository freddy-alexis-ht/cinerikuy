package com.cinerikuy.product.controller;

import com.cinerikuy.product.entity.Product;
import com.cinerikuy.product.exception.AdminException;
import com.cinerikuy.product.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /** POR CUESTIONES DE INTEGRIDAD DE LA DATA, EL BORRADO FÍSICO NO ES POSIBLE, SOLO BORRADO LÓGICO */

    @Tag(name = "admin-product")
    @Operation(summary = "Recupera todos los productos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todos los productos recuperados con éxito", content = @Content),
            @ApiResponse(responseCode = "412", description = "No hay productos en la base de datos", content = @Content)})
    @GetMapping("/products")
    public ResponseEntity<List<Product>> productFindAll() throws AdminException {
        List<Product> list = adminService.productFindAll();
        if(list == null)
            throw new AdminException("C001", "No hay productos en la base de datos.", HttpStatus.PRECONDITION_FAILED);
        return ResponseEntity.ok(list);
    }

    @Tag(name = "admin-product")
    @Operation(summary = "Recupera 1 producto por su 'id'.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto recuperado con éxito", content = @Content),
            @ApiResponse(responseCode = "412", description = "No existe un producto con ese 'id'", content = @Content)})
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> productFindById(@PathVariable long id) throws AdminException {
        Product product = adminService.productFindById(id);
        if(product == null)
            throw new AdminException("C001", "El producto buscado no existe.", HttpStatus.PRECONDITION_FAILED);
        return ResponseEntity.ok(product);
    }

    @Tag(name = "admin-product")
    @Operation(summary = "Guarda/Actualiza el producto enviado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto guardado/actualizado con éxito", content = @Content),
            @ApiResponse(responseCode = "412", description = "Error al momento de guardar/actualizar el producto enviado", content = @Content)})
    @PostMapping("/products")
    public ResponseEntity<Product> productSaveUpdate(@RequestBody Product input) throws AdminException {
        Product save;
        try{
            save = adminService.productSaveUpdate(input);
        }catch (Exception e) {
            throw new AdminException("C001", "Error en la data enviada.", HttpStatus.PRECONDITION_FAILED);
        }
        return ResponseEntity.ok(save);
    }

}

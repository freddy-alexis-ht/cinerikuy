package com.cinerikuy.transaction.controller;

import com.cinerikuy.transaction.dto.TransactionBillingResponse;
import com.cinerikuy.transaction.entity.Billing;
import com.cinerikuy.transaction.entity.ProductData;
import com.cinerikuy.transaction.entity.Transaction;
import com.cinerikuy.transaction.exception.AdminException;
import com.cinerikuy.transaction.exception.BusinessRuleException;
import com.cinerikuy.transaction.service.AdminService;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private TransactionController transactionController;

    /** POR CUESTIONES DE SEGURIDAD Y AUDITORÍA, EL ADMIN NO PUEDE INSERTAR/EDITAR/BORRAR DATA */

    /** TRANSACTION */

    @Tag(name = "admin-transaction")
    @Operation(summary = "Recupera todas las transacciones.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todas las transacciones recuperadas con éxito", content = @Content),
            @ApiResponse(responseCode = "412", description = "No hay transacciones en la base de datos", content = @Content)})
    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> transactionFindAll() throws AdminException {
        List<Transaction> list = adminService.transactionFindAll();
        if(list == null)
            throw new AdminException("C001", "No hay transacciones en la base de datos.", HttpStatus.PRECONDITION_FAILED);
        return ResponseEntity.ok(list);
    }

    @Tag(name = "admin-transaction")
    @Operation(summary = "Recupera 1 transacción por su 'id'.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transacción recuperada con éxito", content = @Content),
            @ApiResponse(responseCode = "412", description = "No existe transacción con ese 'id'", content = @Content)})
    @GetMapping("/transactions/{id}")
    public ResponseEntity<Transaction> transactionFindById(@PathVariable long id) throws AdminException {
        Transaction transaction = adminService.transactionFindById(id);
        if(transaction == null)
            throw new AdminException("C001", "La transacción buscada no existe.", HttpStatus.PRECONDITION_FAILED);
        return ResponseEntity.ok(transaction);
    }

    /** PRODUCT-DATA */

    @Tag(name = "admin-productData")
    @Operation(summary = "Recupera los productos adquiridos en todas las transacciones.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todos los productos de las transacciones recuperados con éxito", content = @Content),
            @ApiResponse(responseCode = "412", description = "No hay productos en las transacciones en la base de datos", content = @Content)})
    @GetMapping("/productsData")
    public ResponseEntity<List<ProductData>> productDataFindAll() throws AdminException {
        List<ProductData> list = adminService.productDataFindAll();
        if(list == null)
            throw new AdminException("C001", "No hay productos en las transacciones en la base de datos.", HttpStatus.PRECONDITION_FAILED);
        return ResponseEntity.ok(list);
    }

    @Tag(name = "admin-productData")
    @Operation(summary = "Recupera el producto de una transacción por su 'id'.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto recuperado con éxito", content = @Content),
            @ApiResponse(responseCode = "412", description = "No existe producto con ese 'id'", content = @Content)})
    @GetMapping("/productsData/id/{id}")
    public ResponseEntity<ProductData> productDataFindById(@PathVariable long id) throws AdminException {
        ProductData productData = adminService.productDataFindById(id);
        if(productData == null)
            throw new AdminException("C001", "El producto buscado no existe.", HttpStatus.PRECONDITION_FAILED);
        return ResponseEntity.ok(productData);
    }

    @Tag(name = "admin-productData")
    @Operation(summary = "Recupera los productos adquiridos en 1 transacción.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todos los productos de 1 transacción recuperados con éxito", content = @Content),
            @ApiResponse(responseCode = "412", description = "No hay productos en esa transacción en la base de datos", content = @Content)})
    @GetMapping("/productsData/transactionId/{transactionId}")
    public ResponseEntity<List<ProductData>> productDataFindByTransactionId(@PathVariable long transactionId) throws AdminException {
        List<ProductData> list = adminService.productDataFindByTransactionId(transactionId);
        if(list == null)
            throw new AdminException("C001", "No hay productos en esa transacción en la base de datos.", HttpStatus.PRECONDITION_FAILED);
        return ResponseEntity.ok(list);
    }

    /** BILLING */

    @Tag(name = "admin-billing")
    @Operation(summary = "Recupera las compras efectuadas por todos los usuarios.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todas las compras recuperadas con éxito", content = @Content),
            @ApiResponse(responseCode = "412", description = "No hay compras en la base de datos", content = @Content)})
    @GetMapping("/billings")
    public ResponseEntity<List<Billing>> billingFindAll() throws AdminException {
        List<Billing> list = adminService.billingFindAll();
        if(list == null)
            throw new AdminException("C001", "No hay compras en la base de datos.", HttpStatus.PRECONDITION_FAILED);
        return ResponseEntity.ok(list);
    }

    @Tag(name = "admin-billing")
    @Operation(summary = "Recupera 1 compra por su 'id'.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Compra recuperada con éxito", content = @Content),
            @ApiResponse(responseCode = "412", description = "No existe compra con ese 'id'", content = @Content)})
    @GetMapping("/billings/id/{id}")
    public ResponseEntity<Billing> billingFindById(@PathVariable long id) throws AdminException {
        Billing billing = adminService.billingFindById(id);
        if(billing == null)
            throw new AdminException("C001", "La compra buscada no existe.", HttpStatus.PRECONDITION_FAILED);
        return ResponseEntity.ok(billing);
    }

    @Tag(name = "admin-billing")
    @Operation(summary = "Recupera las compras efectuadas por 1 usuario.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todas las compras de 1 usuario recuperadas con éxito", content = @Content),
            @ApiResponse(responseCode = "412", description = "El usuario no ha hecho compras", content = @Content)})
    @GetMapping("/billings/username/{username}")
    public ResponseEntity<List<TransactionBillingResponse>> billingFindByUsername(@PathVariable String username) throws AdminException, BusinessRuleException {
        return transactionController.findBillings(username);
    }


}

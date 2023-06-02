package com.cinerikuy.product.controller;

import com.cinerikuy.product.dto.ProductResponse;
import com.cinerikuy.product.dto.ProductResponseMapper;
import com.cinerikuy.product.entity.Product;
import com.cinerikuy.product.exception.ProductListException;
import com.cinerikuy.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductResponseMapper proResMapper;

    /** ALL THE PRODUCTS */
    @Operation(summary = "Get all products.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All products returned", content = @Content),
            @ApiResponse(responseCode = "412", description = "There are no products", content = @Content)})
    @GetMapping()
    public ResponseEntity<List<ProductResponse>> findAll() throws ProductListException {
        List<Product> list = productService.findAll();
        if(list == null)
            throw new ProductListException("P001", "No hay productos en la DB.", HttpStatus.PRECONDITION_FAILED);
        List<ProductResponse> response = proResMapper.ProductListToProductResponseList(list);
        return ResponseEntity.ok(response);
    }

}

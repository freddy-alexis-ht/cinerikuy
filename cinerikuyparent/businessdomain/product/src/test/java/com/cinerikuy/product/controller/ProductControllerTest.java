package com.cinerikuy.product.controller;

import com.cinerikuy.product.dto.ProductResponse;
import com.cinerikuy.product.dto.ProductResponseMapper;
import com.cinerikuy.product.entity.Product;
import com.cinerikuy.product.exception.ProductListException;
import com.cinerikuy.product.service.ProductService;
import com.cinerikuy.product.util.Data;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private ProductService productService;
    @MockBean
    private ProductResponseMapper proResMapper;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("findAll() - return all products when GET method and /products endpoint is called")
    void returnAllProductsWhenGetMethodAndProductsEndpointIsCalled() throws Exception {
        // ARRANGE
        List<Product> products = Arrays.asList(Data.createProduct01().get(), Data.createProduct02().get());
        List<ProductResponse> productResponses = Arrays.asList(Data.createProductResponse01().get(),
                Data.createProductResponse02().get());
        when(productService.findAll()).thenReturn(products);
        when(proResMapper.ProductListToProductResponseList(products)).thenReturn(productResponses);

        // ACT
        mvc.perform(get("/products").contentType(MediaType.APPLICATION_JSON))

        // ASSERT
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].productCode").value("P01"))
                .andExpect(jsonPath("$[0].name").value("Popcorn Pequeño"))
                .andExpect(jsonPath("$[0].imageUrl").value("https://..."))
                .andExpect(jsonPath("$[0].price").value("7.0"))
                .andExpect(jsonPath("$[1].productCode").value("P02"))
                .andExpect(jsonPath("$[1].name").value("Popcorn Mediano"))
                .andExpect(jsonPath("$[1].imageUrl").value("https://..."))
                .andExpect(jsonPath("$[1].price").value("10.0"))
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(content().json(objectMapper.writeValueAsString(productResponses)));

        verify(productService).findAll();
        verify(proResMapper).ProductListToProductResponseList(products);
    }

    @Test
    @DisplayName("findAll() - throw Exception when GET method and /products endpoint is called and list is null")
    void throwExceptionWhenGetMethodAndProductsEndpointIsCalledAndListIsNull() throws Exception {
        // ARRANGE
        String exceptionParam = "precondition_failed";
        when(productService.findAll()).thenReturn(null);
        // ACT
        mvc.perform(get("/products", exceptionParam).contentType(MediaType.APPLICATION_JSON))
        // ASSERT
                .andExpect(status().isPreconditionFailed())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ProductListException))
                .andExpect(result -> assertEquals("No hay productos en la DB.",
                        result.getResolvedException().getMessage()));

        verify(productService).findAll();
    }

    @Test
    @DisplayName("findByProductCode(productCode) - return product when GET method and /productCode/{productCode} endpoint is called")
    void returnProductWhenGetMethodAndProductCodeEndpointIsCalled() throws Exception {
        Product product = Data.createProduct01().get();
        when(productService.findByProductCode("P01")).thenReturn(product);

        mvc.perform(get("/products/productCode/{productCode}", "P01").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.productCode").value("P01"))
                .andExpect(jsonPath("$.name").value("Popcorn Pequeño"))
                .andExpect(jsonPath("$.imageUrl").value("https://..."))
                .andExpect(jsonPath("$.price").value("7.0"))
                .andExpect(content().json(objectMapper.writeValueAsString(product)));

        verify(productService).findByProductCode("P01");
    }

    @Test
    @DisplayName("findByProductCode(productCode) - throw Exception when GET method and /productCode/{productCode} endpoint is called and product is null")
    void throwExceptionWhenGetMethodAndProductCodeEndpointIsCalledAndProductIsNull() throws Exception {

        when(productService.findByProductCode("PXX")).thenReturn(null);
        String exceptionParam = "precondition_failed";

        mvc.perform(get("/products/productCode/{productCode}", "PXX").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isPreconditionFailed())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ProductListException))
                .andExpect(result -> assertEquals("ProductCode no existe.", result.getResolvedException().getMessage()));

        verify(productService).findByProductCode("PXX");
    }

}
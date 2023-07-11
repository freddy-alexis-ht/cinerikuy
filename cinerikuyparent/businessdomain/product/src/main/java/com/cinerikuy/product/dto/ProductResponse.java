package com.cinerikuy.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "ProductResponse", description = "Object that returns Product information.")
public class ProductResponse {

    @Schema(name = "productCode", example = "P01", description = "Unique product code")
    private String productCode;
    @Schema(name = "name", example = "Porcorn Pequeño", description = "Product name")
    private String name;
    @Schema(name = "imageUrl", example = "http://abc", description = "Product image URL")
    private String imageUrl;
    @Schema(name = "price", example = "S/ 8.00", description = "Product price")
    private String price;
}

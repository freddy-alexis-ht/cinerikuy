package com.cinerikuy.product.dto;

import com.cinerikuy.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductResponseMapper {

    @Mapping(source = "price", target = "price")
    ProductResponse ProductToProductResponse(Product product);

    List<ProductResponse> ProductListToProductResponseList(List<Product> source);

}
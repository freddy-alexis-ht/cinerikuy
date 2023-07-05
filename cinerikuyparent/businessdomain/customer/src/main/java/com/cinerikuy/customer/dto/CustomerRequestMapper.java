package com.cinerikuy.customer.dto;

import com.cinerikuy.customer.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CustomerRequestMapper {
    @Mappings({
//            @Mapping(target = "role", constant = "customer"),
            @Mapping(target = "enabled", constant = "true")
    })
    Customer CustomerSignInRequestToCustomer(CustomerSignInRequest source);

}

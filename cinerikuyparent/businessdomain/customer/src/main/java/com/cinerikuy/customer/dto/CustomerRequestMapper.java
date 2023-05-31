package com.cinerikuy.customer.dto;

import com.cinerikuy.customer.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CustomerRequestMapper {
    @Mappings({ // The source in this case is InvoiceRequest
            @Mapping(target = "role", constant = "customer"),
            @Mapping(target = "state", constant = "1"),
            @Mapping(target = "hasVoted", constant = "true")
    })
    Customer CustomerSignInRequestToCustomer(CustomerSignInRequest source);

}

package com.cinerikuy.customer.dto;

import com.cinerikuy.customer.entity.Customer;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerResponseMapper {

    Customer CustomerResponseToCustomer(CustomerResponse source);

    @InheritInverseConfiguration
    CustomerResponse CustomerToCustomerResponse(Customer source);

}

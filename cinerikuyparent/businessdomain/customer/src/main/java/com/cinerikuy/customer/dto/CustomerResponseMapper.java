package com.cinerikuy.customer.dto;

import com.cinerikuy.customer.entity.Customer;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerResponseMapper {

    @Mappings({})
    Customer CustomerResponseToCustomer(CustomerResponse source);
    List<Customer> CustomerResponseListToCustomerList(List<CustomerResponse> source);

    @InheritInverseConfiguration
    CustomerResponse CustomerToCustomerResponse(Customer source);

    @InheritInverseConfiguration
    List<CustomerResponse> CustomerListToCustomerResponseList(List<Customer> source);
}

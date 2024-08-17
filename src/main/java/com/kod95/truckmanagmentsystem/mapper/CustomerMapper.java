package com.kod95.truckmanagmentsystem.mapper;

import com.kod95.truckmanagmentsystem.dto.CustomerDto;
import com.kod95.truckmanagmentsystem.dto.request.CustomerRequest;
import com.kod95.truckmanagmentsystem.model.admin.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDto entityToDto(Customer customer);
    Customer requestToEntity(CustomerRequest request);
    List<CustomerDto> entityListToDtoList(List<Customer> customerList);
}

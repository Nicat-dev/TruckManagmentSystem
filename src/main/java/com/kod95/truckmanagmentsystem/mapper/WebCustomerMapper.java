package com.kod95.truckmanagmentsystem.mapper;

import com.kod95.truckmanagmentsystem.dto.WebCustomerDto;
import com.kod95.truckmanagmentsystem.dto.request.WebCustomerRequest;
import com.kod95.truckmanagmentsystem.model.admin.WebCustomer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WebCustomerMapper {

    WebCustomerDto entityToDto(WebCustomer webCustomer);
    WebCustomer requestToEntity(WebCustomerRequest request);
    List<WebCustomerDto> dtoListToEntityList(List<WebCustomer> webCustomers);

}

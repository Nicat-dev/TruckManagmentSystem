package com.kod95.truckmanagmentsystem.service;

import com.kod95.truckmanagmentsystem.dto.WebCustomerDto;
import com.kod95.truckmanagmentsystem.dto.request.WebCustomerRequest;

import java.util.List;

public interface WebCustomerService {
    WebCustomerDto save(WebCustomerRequest request);
    WebCustomerDto get(Long id);
    List<WebCustomerDto> getAll();
    void delete(Long id);

}

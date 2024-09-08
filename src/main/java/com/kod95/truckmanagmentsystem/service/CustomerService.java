package com.kod95.truckmanagmentsystem.service;

import com.kod95.truckmanagmentsystem.dto.CustomerDto;
import com.kod95.truckmanagmentsystem.dto.CustomerStatusDto;
import com.kod95.truckmanagmentsystem.dto.request.CustomerRequest;
import com.kod95.truckmanagmentsystem.model.admin.Users;
import com.kod95.truckmanagmentsystem.model.enums.CustomerStatus;

import java.util.List;

public interface CustomerService {
    CustomerDto save(CustomerRequest request);
    List<CustomerDto> getAll();
    CustomerDto get(Long id);
    CustomerStatusDto statusChanger(Long id, CustomerStatus status);
    void delete(Long id);
    CustomerDto update(Long id, CustomerRequest request);
    void deactivate(Long id);
    void activate(Long id);
    List<CustomerDto> getAllActive();
    List<CustomerDto> getAllInactive();
    Long countActiveCustomers();
    Long countInactiveCustomers();
    Long countPendingCustomers();
    Long countCustomersByUserId(Long id);
}

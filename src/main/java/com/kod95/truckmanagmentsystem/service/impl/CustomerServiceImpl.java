package com.kod95.truckmanagmentsystem.service.impl;

import com.kod95.truckmanagmentsystem.dto.CustomerDto;
import com.kod95.truckmanagmentsystem.dto.CustomerStatusDto;
import com.kod95.truckmanagmentsystem.dto.request.CustomerRequest;
import com.kod95.truckmanagmentsystem.exception.ApplicationException;
import com.kod95.truckmanagmentsystem.mapper.CustomerMapper;
import com.kod95.truckmanagmentsystem.model.admin.Customer;
import com.kod95.truckmanagmentsystem.model.enums.CustomerStatus;
import com.kod95.truckmanagmentsystem.model.enums.Exceptions;
import com.kod95.truckmanagmentsystem.repository.CustomerRepository;
import com.kod95.truckmanagmentsystem.service.CustomerService;
import com.kod95.truckmanagmentsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;
    private final UserService userService;

    @Override
    public CustomerDto save(CustomerRequest request) {
        Customer customer = mapper.requestToEntity(request);
        customer.setUsers(userService.findUser(request.userId()));
        return mapper.entityToDto(repository.save(customer));
    }

    @Override
    public List<CustomerDto> getAll() {
        return mapper.entityListToDtoList(repository.findAll());
    }

    @Override
    public CustomerDto get(Long id) {
        return mapper.entityToDto(find(id));
    }

    @Override
    public List<CustomerDto> getByName(String name) {
        return mapper.entityListToDtoList(repository.findAll());
    }

    @Override
    public CustomerStatusDto statusChanger(Long id, CustomerStatus status) {
        Customer customer = find(id);

        customer.setId(id);
        customer.setCustomerStatus(status);
        repository.save(customer);
        return new CustomerStatusDto(customer.getId(), customer.getCustomerStatus());
    }

    @Override
    public void delete(Long id) {
        repository.delete(find(id));
    }

    @Override
    public CustomerDto update(Long id, CustomerRequest request) {
        return repository.findById(id).map(customer -> {
            Customer customerMapper = mapper.requestToEntity(request);
            customerMapper.setId(id);
            return mapper.entityToDto(repository.save(customer));
        }).orElseThrow(() -> new ApplicationException(Exceptions.CUSTOMER_CANNOT_FOUND));
    }

    @Override
    public void deactivate(Long id) {
        Customer customer = find(id);
        customer.setCustomerStatus(CustomerStatus.DE_ACTIVE);
        repository.save(customer);
    }

    @Override
    public void activate(Long id) {
        Customer customer = find(id);
        customer.setCustomerStatus(CustomerStatus.ACTIVE);
        repository.save(customer);
    }

    @Override
    public List<CustomerDto> getAllActive() {
        return mapper.entityListToDtoList(repository.findAllByCustomerStatus(CustomerStatus.ACTIVE));
    }

    @Override
    public List<CustomerDto> getAllInactive() {
        return mapper.entityListToDtoList(repository.findAllByCustomerStatus(CustomerStatus.DE_ACTIVE));
    }

    @Override
    public Long countActiveCustomers() {
        return repository.countByCustomerStatus(CustomerStatus.ACTIVE);
    }

    @Override
    public Long countInactiveCustomers() {
        return repository.countByCustomerStatus(CustomerStatus.DE_ACTIVE);
    }

    @Override
    public Long countPendingCustomers() {
        return repository.countByCustomerStatus(CustomerStatus.PENDING);
    }

    private Customer find(Long id){
        return repository.findById(id)
                .orElseThrow(()-> new ApplicationException(Exceptions.CUSTOMER_CANNOT_FOUND));
    }
}

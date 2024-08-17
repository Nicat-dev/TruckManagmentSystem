package com.kod95.truckmanagmentsystem.service.impl;

import com.kod95.truckmanagmentsystem.dto.WebCustomerDto;
import com.kod95.truckmanagmentsystem.dto.request.WebCustomerRequest;
import com.kod95.truckmanagmentsystem.exception.ApplicationException;
import com.kod95.truckmanagmentsystem.mapper.WebCustomerMapper;
import com.kod95.truckmanagmentsystem.model.admin.WebCustomer;
import com.kod95.truckmanagmentsystem.model.enums.Exceptions;
import com.kod95.truckmanagmentsystem.repository.WebCustomerRepository;
import com.kod95.truckmanagmentsystem.service.WebCustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WebCustomerServiceImpl implements WebCustomerService {

    private final WebCustomerRepository repository;
    private final WebCustomerMapper mapper;

    @Override
    public WebCustomerDto save(WebCustomerRequest request) {
        return mapper.entityToDto(repository.save(mapper.requestToEntity(request)));
    }

    @Override
    public WebCustomerDto get(Long id) {
        return mapper.entityToDto(findWebCustomer(id));
    }

    @Override
    public List<WebCustomerDto> getAll() {
        return mapper.dtoListToEntityList(repository.findAll());
    }

    @Override
    public void delete(Long id) {
        repository.delete(findWebCustomer(id));
    }

    private WebCustomer findWebCustomer(Long id){
        return repository.findById(id)
                .orElseThrow(()-> new ApplicationException(Exceptions.USER_NOT_FOUND_EXCEPTION));
    }
}

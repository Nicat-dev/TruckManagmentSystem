package com.kod95.truckmanagmentsystem.service.impl;

import com.kod95.truckmanagmentsystem.dto.UsersDto;
import com.kod95.truckmanagmentsystem.dto.request.UserRequest;
import com.kod95.truckmanagmentsystem.exception.ApplicationException;
import com.kod95.truckmanagmentsystem.mapper.UserMapper;
import com.kod95.truckmanagmentsystem.model.admin.Customer;
import com.kod95.truckmanagmentsystem.model.admin.Users;
import com.kod95.truckmanagmentsystem.model.enums.Exceptions;
import com.kod95.truckmanagmentsystem.repository.UsersRepository;
import com.kod95.truckmanagmentsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UsersRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;  // Use PasswordEncoder instead of EncryptionUtils

    @Override
    public List<UsersDto> getList() {
        return mapper.entityListToDtoList(repository.findAll());
    }

    @Override
    public UsersDto get(Long id) {
        return mapper.entityToDto(findUser(id));
    }

    @Override
    public UsersDto update(Long id, UserRequest request) {
        return repository.findById(id).map(users -> {
            Users usersMapper = mapper.requestToEntity(request);
            return mapper.entityToDto(repository.save(usersMapper));
        }).orElseThrow(() -> new ApplicationException(Exceptions.USER_NOT_FOUND_EXCEPTION));
    }

    @Override
    public UsersDto save(UserRequest request) {
        Users newUser = mapper.requestToEntity(request);
        newUser.setPassword(passwordEncoder.encode(request.password()));  // Hash the password
        return mapper.entityToDto(repository.save(newUser));
    }

    @Override
    public void delete(Long id) {
        repository.delete(findUser(id));
    }

    @Override
    public BigDecimal getRevenue(Long id) {
        Users user = findUser(id);
        return user.getCustomers().stream()
                .map(Customer::getCustomerRevenue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public Users findUser(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ApplicationException(Exceptions.USER_NOT_FOUND_EXCEPTION));
    }
}

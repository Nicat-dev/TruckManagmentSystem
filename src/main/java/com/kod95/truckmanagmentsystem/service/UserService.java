package com.kod95.truckmanagmentsystem.service;

import com.kod95.truckmanagmentsystem.dto.UsersDto;
import com.kod95.truckmanagmentsystem.dto.request.UserRequest;
import com.kod95.truckmanagmentsystem.model.admin.Users;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {

    List<UsersDto> getList();
    UsersDto get(Long id);
    UsersDto update(Long id, UserRequest request);
    UsersDto save(UserRequest request) throws Exception;
    void delete(Long id);
    Users findUser(Long id);
    BigDecimal getRevenue(Long id);
    void renewPassword(Long id,String password);

}

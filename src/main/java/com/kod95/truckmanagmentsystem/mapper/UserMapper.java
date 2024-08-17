package com.kod95.truckmanagmentsystem.mapper;


import com.kod95.truckmanagmentsystem.dto.UsersDto;
import com.kod95.truckmanagmentsystem.dto.request.UserRequest;
import com.kod95.truckmanagmentsystem.model.admin.Users;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UsersDto entityToDto(Users users);
    Users requestToDto(UserRequest request);
    List<UsersDto> entityListToDtoList(List<Users> usersList);

}

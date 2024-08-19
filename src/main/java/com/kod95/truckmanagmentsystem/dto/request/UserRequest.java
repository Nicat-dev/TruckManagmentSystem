package com.kod95.truckmanagmentsystem.dto.request;

import com.kod95.truckmanagmentsystem.model.enums.UserAuth;

public record UserRequest(
        String name,
        String surname,
        String email,
        Integer age,
        String username,
        String password,
        UserAuth userAuth) {
}

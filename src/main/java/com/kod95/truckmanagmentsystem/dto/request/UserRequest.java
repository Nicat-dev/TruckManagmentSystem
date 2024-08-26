package com.kod95.truckmanagmentsystem.dto.request;

import com.kod95.truckmanagmentsystem.model.enums.UserAuth;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(
        @NotBlank(message = "name cannot be blank")
        String name,
        @NotBlank(message = "surname cannot be blank")
        String surname,
        @NotBlank(message = "email cannot be blank")
        String email,
        @NotBlank(message = "age cannot be blank")
        Integer age,
        @NotBlank(message = "username cannot be blank")
        String username,
        @NotBlank(message = "password cannot be blank")
        String password,
        UserAuth userAuth) {
}

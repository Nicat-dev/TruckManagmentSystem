package com.kod95.truckmanagmentsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthenticationRequest(
        @NotBlank(message = "username cannot be null")
        String username,
        @NotBlank(message = "password cannot be null")
        @Size(min = 6)
        String password
) {
}

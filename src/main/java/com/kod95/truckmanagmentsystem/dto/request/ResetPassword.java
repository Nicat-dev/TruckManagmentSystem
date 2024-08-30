package com.kod95.truckmanagmentsystem.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ResetPassword(
        @NotBlank(message = "password cannot be null")
        String password
) {
}

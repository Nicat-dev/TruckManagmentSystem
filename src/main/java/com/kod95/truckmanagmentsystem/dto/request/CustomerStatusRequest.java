package com.kod95.truckmanagmentsystem.dto.request;

import com.kod95.truckmanagmentsystem.model.enums.CustomerStatus;
import jakarta.validation.constraints.NotBlank;

public record CustomerStatusRequest(
        @NotBlank(message = "user status cannot be null")
        CustomerStatus customerStatus
) {
}

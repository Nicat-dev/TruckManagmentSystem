package com.kod95.truckmanagmentsystem.dto.request;

import com.kod95.truckmanagmentsystem.model.enums.CustomerLocationStatus;
import jakarta.validation.constraints.NotBlank;

public record WebCustomerRequest(
        @NotBlank(message = "name cannot be null")
        String name,
        @NotBlank(message = "number cannot be null")
        String number,
        CustomerLocationStatus customerLocationStatus
) {
}

package com.kod95.truckmanagmentsystem.dto.request;

import com.kod95.truckmanagmentsystem.model.enums.CustomerLocationStatus;
import com.kod95.truckmanagmentsystem.model.enums.CustomerStatus;
import com.kod95.truckmanagmentsystem.model.enums.ProcedureEnum;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record CustomerRequest(
        @NotBlank(message = "name cannot be blank")
        String name,
        @NotBlank(message = "surname cannot be blank")
        String surname,
        String email,
        @NotBlank(message = "customer revenue cannot be null")
        BigDecimal customerRevenue,
        @NotBlank(message = "customer number cannot be null")
        String number,
        Long userId,
        ProcedureEnum procedure,
        CustomerStatus customerStatus,
        CustomerLocationStatus customerLocationStatus
) {
}

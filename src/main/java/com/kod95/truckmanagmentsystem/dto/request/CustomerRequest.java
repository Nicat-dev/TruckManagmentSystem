package com.kod95.truckmanagmentsystem.dto.request;

import com.kod95.truckmanagmentsystem.model.enums.CustomerLocationStatus;
import com.kod95.truckmanagmentsystem.model.enums.CustomerStatus;
import com.kod95.truckmanagmentsystem.model.enums.ProcedureEnum;

import java.math.BigDecimal;

public record CustomerRequest(
        String name,
        String surname,
        String email,
        BigDecimal customerRevenue,
        String number,
        Long userId,
        ProcedureEnum procedure,
        CustomerStatus customerStatus,
        CustomerLocationStatus customerLocationStatus
) {
}

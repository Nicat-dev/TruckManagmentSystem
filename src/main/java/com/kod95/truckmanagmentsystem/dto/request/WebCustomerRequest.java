package com.kod95.truckmanagmentsystem.dto.request;

import com.kod95.truckmanagmentsystem.model.enums.CustomerLocationStatus;

public record WebCustomerRequest(
        String name,
        String number,
        CustomerLocationStatus customerLocationStatus
) {
}

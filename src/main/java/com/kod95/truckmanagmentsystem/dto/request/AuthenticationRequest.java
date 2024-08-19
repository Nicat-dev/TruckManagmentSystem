package com.kod95.truckmanagmentsystem.dto.request;

public record AuthenticationRequest(
        String username,
        String password
) {
}

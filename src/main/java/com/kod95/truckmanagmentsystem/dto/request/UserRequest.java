package com.kod95.truckmanagmentsystem.dto.request;

public record UserRequest(
        String name,
        String surname,
        String email,
        Integer age ) {
}

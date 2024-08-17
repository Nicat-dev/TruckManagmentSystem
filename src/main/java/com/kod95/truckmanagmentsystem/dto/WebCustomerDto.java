package com.kod95.truckmanagmentsystem.dto;

import com.kod95.truckmanagmentsystem.model.enums.CustomerLocationStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WebCustomerDto {
    Long id;
    String name;
    String number;
    CustomerLocationStatus customerLocationStatus;
}

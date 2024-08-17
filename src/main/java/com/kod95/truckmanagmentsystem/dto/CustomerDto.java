package com.kod95.truckmanagmentsystem.dto;

import com.kod95.truckmanagmentsystem.model.enums.CustomerLocationStatus;
import com.kod95.truckmanagmentsystem.model.enums.CustomerStatus;
import com.kod95.truckmanagmentsystem.model.enums.ProcedureEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerDto {
    Long id;
    String name;
    String surname;
    String email;
    String number;
    BigDecimal customerRevenue;
    ProcedureEnum procedure;
    CustomerStatus customerStatus;
    CustomerLocationStatus customerLocationStatus;
}

package com.kod95.truckmanagmentsystem.dto;

import com.kod95.truckmanagmentsystem.model.enums.CustomerStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerStatusDto {

    Long id;
    CustomerStatus customerStatus;

}

package com.kod95.truckmanagmentsystem.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UsersDto {

    Long id;
    String name;
    String surname;
    String email;
    Integer age;
    BigDecimal totalRevenue;

}

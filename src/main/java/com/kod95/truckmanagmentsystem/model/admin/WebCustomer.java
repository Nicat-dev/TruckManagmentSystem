package com.kod95.truckmanagmentsystem.model.admin;

import com.kod95.truckmanagmentsystem.model.enums.CustomerLocationStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "web_customer")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WebCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "web_customer_name")
    String name;
    @Column(name = "web_customer_number")
    String number;
    @Column(name = "web_customer_location")
    CustomerLocationStatus customerLocationStatus;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false,updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDateTime createdAt;

}

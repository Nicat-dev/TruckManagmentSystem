package com.kod95.truckmanagmentsystem.model.admin;

import com.kod95.truckmanagmentsystem.model.enums.CustomerLocationStatus;
import com.kod95.truckmanagmentsystem.model.enums.CustomerStatus;
import com.kod95.truckmanagmentsystem.model.enums.ProcedureEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "name", nullable = false)
    String name;
    @Column(name = "surname", nullable = false)
    String surname;
    @Column(name = "number", nullable = false)
    String number;
    @Column(name = "procedure", nullable = false)
    @Enumerated(EnumType.STRING)
    ProcedureEnum procedure;
    @Column(name = "customer_status", nullable = false)
    @Enumerated(EnumType.STRING)
    CustomerStatus customerStatus;
    @Column(name = "email")
    String email;
    @Column(name = "customer_location_status", nullable = false)
    @Enumerated(EnumType.STRING)
    CustomerLocationStatus customerLocationStatus;
    @Column(name = "customer_revenue")
    BigDecimal customerRevenue;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false,updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

}

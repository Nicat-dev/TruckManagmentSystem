package com.kod95.truckmanagmentsystem.repository;

import com.kod95.truckmanagmentsystem.model.admin.Customer;
import com.kod95.truckmanagmentsystem.model.enums.CustomerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    List<Customer> findAllByCustomerStatus(CustomerStatus customerStatus);
    Long countByCustomerStatus(CustomerStatus status);  // Add this method
}

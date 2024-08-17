package com.kod95.truckmanagmentsystem.repository;

import com.kod95.truckmanagmentsystem.model.admin.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}

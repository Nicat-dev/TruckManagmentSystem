package com.kod95.truckmanagmentsystem.repository;

import com.kod95.truckmanagmentsystem.model.admin.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {

    Optional<Users> findByUsername(String username);

}

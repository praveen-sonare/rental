package com.scooterson.rental.repository;

import com.scooterson.rental.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Integer> {
    Tenant findByUsername(String username);
}

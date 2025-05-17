package com.projects.shopIt.repositories;

import com.projects.shopIt.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}

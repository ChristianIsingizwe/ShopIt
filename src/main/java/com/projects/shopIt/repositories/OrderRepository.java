package com.projects.shopIt.repositories;

import com.projects.shopIt.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
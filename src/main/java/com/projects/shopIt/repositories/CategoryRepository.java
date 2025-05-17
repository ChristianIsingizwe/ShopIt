package com.projects.shopIt.repositories;

import com.projects.shopIt.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}

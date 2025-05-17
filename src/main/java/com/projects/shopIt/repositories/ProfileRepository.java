package com.projects.shopIt.repositories;

import com.projects.shopIt.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}

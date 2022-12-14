package io.codingtest.springboot.repository;

import io.codingtest.springboot.models.data.Center;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CenterRepository extends JpaRepository<Center,Long> {
    Optional<Center> findByName(String name);
}

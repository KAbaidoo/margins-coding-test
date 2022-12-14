package io.codingtest.springboot.repository;

import io.codingtest.springboot.models.data.Verification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationRepository extends JpaRepository<Verification, Long> {
    Optional<Verification> findByPinNumber(String pinNumber);

    Boolean existsByPinNumber(String pinNumber);
}

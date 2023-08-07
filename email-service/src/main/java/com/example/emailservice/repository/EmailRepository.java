package com.example.emailservice.repository;

import com.example.emailservice.entity.EmailRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailRepository extends JpaRepository<EmailRequest, String> {
    Optional<EmailRequest> findByEmailTo(String emailTo);
}

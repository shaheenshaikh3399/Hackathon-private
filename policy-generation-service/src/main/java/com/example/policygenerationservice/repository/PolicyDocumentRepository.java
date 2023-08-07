package com.example.policygenerationservice.repository;

import com.example.policygenerationservice.entity.PolicyDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyDocumentRepository extends JpaRepository<PolicyDocument, String> {
}

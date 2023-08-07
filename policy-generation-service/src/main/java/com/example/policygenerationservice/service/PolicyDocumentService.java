package com.example.policygenerationservice.service;

import com.example.policygenerationservice.dto.DocumentResponseDto;
import com.example.policygenerationservice.dto.PolicyDocumentDto;
import com.example.policygenerationservice.entity.PolicyDocument;

import java.util.List;

public interface PolicyDocumentService {
    DocumentResponseDto generateDocument(PolicyDocument policyDocument, String transactionId);

    PolicyDocumentDto getPolicyDocumentById(String documentId);

    List<PolicyDocumentDto> getAll();
}

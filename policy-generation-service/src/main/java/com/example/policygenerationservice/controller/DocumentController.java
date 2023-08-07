package com.example.policygenerationservice.controller;

import com.example.policygenerationservice.dto.DocumentResponseDto;
import com.example.policygenerationservice.dto.PolicyDocumentDto;
import com.example.policygenerationservice.entity.PolicyDocument;
import com.example.policygenerationservice.service.PolicyDocumentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/policies/generate")
@AllArgsConstructor
public class DocumentController {

    private final PolicyDocumentService policyDocumentService;

    //@CircuitBreaker(name = "policyDocumentCircuitBreaker", fallbackMethod = "policyDocumentFallBack")
    @PostMapping("/{id}")
    public ResponseEntity<DocumentResponseDto> generatePolicyDocument(@RequestBody @Valid PolicyDocument policyDocument, @PathVariable("id") String transactionId) {
        return new ResponseEntity<DocumentResponseDto>(policyDocumentService.generateDocument(policyDocument, transactionId), HttpStatus.CREATED);
    }

   /* public ResponseEntity<DocumentResponseDto> policyDocumentFallBack(PolicyDocument policyDocument, String transactionId, Exception e) {
        return new ResponseEntity<>(DocumentResponseDto.builder()
                .documentId("dummy document id")
                .build(), HttpStatus.BAD_REQUEST);
    }*/

    @GetMapping("/getById/{id}")
    public ResponseEntity<PolicyDocumentDto> getPolicyDocumentById(@PathVariable("id") String documentId) {
        return ResponseEntity.ok(policyDocumentService.getPolicyDocumentById(documentId));
    }

    @GetMapping("/")
    public ResponseEntity<List<PolicyDocumentDto>> getAll() {
        return ResponseEntity.ok(policyDocumentService.getAll());
    }
}

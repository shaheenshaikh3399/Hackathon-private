package com.example.emailservice.controller;

import com.example.emailservice.dto.*;
import com.example.emailservice.entity.EmailRequest;
import com.example.emailservice.service.EmailService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/notifications/email")
public class EmailController {

    private final EmailService emailService;

    @CircuitBreaker(name = "createEmailCircuitBreaker", fallbackMethod ="createEmailCircuitFallBack")
    @PostMapping("/{id}")
    public ResponseEntity<EmailRequestDto> createEmail(@RequestBody EmailRequest emailRequest, @PathVariable ("id") String documentId) {
        return new ResponseEntity<>(emailService.createAndSaveEmail(emailRequest, documentId), HttpStatus.CREATED);
    }
    public ResponseEntity<EmailRequestDto> createEmailCircuitFallBack(EmailRequest emailRequest, String documentId, Exception e) {
        return new ResponseEntity<>(EmailRequestDto.builder()
                .from("admin@dummy.com")
                .to("dummy@gmail.com")
                .subject("dummy subject")
                .body(PolicyDocumentDto.builder()
                        .documentId("Dummy document Id")
                        .transactionDetails(TransactionDto.builder()
                                .transactionId("dummy transaction id")
                                .policyId("dummy id")
                                .status("dummy")
                                .build())
                        .policySelectedDetails(PolicySelectionDto.builder()
                                .policySelectionId("dummy id")
                                .email("dummy@gmail.com")
                                .policySelectionId("dummy id")
                                .customerName("Dummy")
                                .policyDetails(PolicyCatalogDto.builder()
                                        .policyId("dummy")
                                        .type("dummy type")
                                        .name("dummy policy")
                                        .amount(00000.00)
                                        .build())
                                .build())
                        .build())
                .build(), HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/")
    public ResponseEntity<List<EmailRequestDto>> getAllNotifications() {
        return ResponseEntity.ok(emailService.getAll());
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<EmailRequestDto> getNotificationById(@PathVariable ("id") String notificationId) {
        return ResponseEntity.ok(emailService.getNotificationById(notificationId));
    }
    @GetMapping("/byName/{name}")
    public ResponseEntity<EmailRequestDto> getNotificationByEmailTo(@PathVariable ("name") String emailTo) {
        return ResponseEntity.ok(emailService.getNotificationByEmailTo(emailTo));
    }
}

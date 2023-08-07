package com.example.emailservice.service.impl;

import com.example.emailservice.dto.EmailRequestDto;
import com.example.emailservice.dto.PolicyDocumentDto;
import com.example.emailservice.entity.EmailRequest;
import com.example.emailservice.exception.GlobalExceptionHandler;
import com.example.emailservice.openfeign.GenerationFeignClient;
import com.example.emailservice.repository.EmailRepository;
import com.example.emailservice.service.EmailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;

    private final GenerationFeignClient generationFeignClient;


    @Override
    public EmailRequestDto createAndSaveEmail(EmailRequest emailRequest, String documentId) {
        log.info("Inside createAndSaveEmail");
        emailRequest.setNotificationId(UUID.randomUUID().toString());
        emailRequest.setDocumentId(documentId);
        return mapToDto(emailRepository.save(emailRequest));
    }

    @Override
    public List<EmailRequestDto> getAll() {
        log.info("Inside getAll");
        return emailRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmailRequestDto getNotificationById(String notificationId) {
        log.info("Inside getNotificationById");
        EmailRequest emailRequest = emailRepository.findById(notificationId).orElseThrow(()-> new GlobalExceptionHandler(
                String.format("Policy document not found with the id: %s", notificationId)
        ));
        return mapToDto(emailRequest);
    }

    @Override
    public EmailRequestDto getNotificationByEmailTo(String emailTo) {
        log.info("Inside getNotificationByEmailTo");
        EmailRequest emailRequest = emailRepository.findByEmailTo(emailTo).orElseThrow(()-> new GlobalExceptionHandler(
                String.format("Policy document not found with the name: %s", emailTo)
        ));
        return mapToDto(emailRequest);
    }

    private EmailRequestDto mapToDto(EmailRequest emailRequest) {
        PolicyDocumentDto policyDocumentDto = generationFeignClient.getPolicyDocumentById(emailRequest.getDocumentId()).getBody();
        assert policyDocumentDto != null;
        return EmailRequestDto.builder()
                .notificationId(emailRequest.getNotificationId())
                .from(emailRequest.getEmailFrom())
                .to(emailRequest.getEmailTo())
                .subject(emailRequest.getEmailSubject())
                .body(PolicyDocumentDto.builder()
                        .documentId(Objects.requireNonNull(policyDocumentDto.getDocumentId()))
                        .policySelectedDetails(Objects.requireNonNull(policyDocumentDto.getPolicySelectedDetails()))
                        .transactionDetails(Objects.requireNonNull(policyDocumentDto.getTransactionDetails()))
                        .build())
                .build();
    }
}

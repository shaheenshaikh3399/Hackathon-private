package com.example.policygenerationservice.service.impl;

import com.example.policygenerationservice.dto.DocumentResponseDto;
import com.example.policygenerationservice.dto.PolicyDocumentDto;
import com.example.policygenerationservice.dto.TransactionDto;
import com.example.policygenerationservice.entity.EmailRequest;
import com.example.policygenerationservice.entity.PaymentStatus;
import com.example.policygenerationservice.entity.PolicyDocument;
import com.example.policygenerationservice.entity.PolicyDocumentConstants;
import com.example.policygenerationservice.exception.GlobalExceptionHandler;
import com.example.policygenerationservice.feignservice.EmailFeignClient;
import com.example.policygenerationservice.feignservice.SelectionFeignClient;
import com.example.policygenerationservice.feignservice.TransactionFeignClient;
import com.example.policygenerationservice.repository.PolicyDocumentRepository;
import com.example.policygenerationservice.service.PolicyDocumentService;
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
public class PolicyDocumentServiceImpl implements PolicyDocumentService {
    private final PolicyDocumentRepository policyDocumentRepository;
    private final EmailFeignClient emailFeignClient;
    private final SelectionFeignClient selectionFeignClient;

    private final TransactionFeignClient transactionFeignClient;

    @Override
    public DocumentResponseDto generateDocument(PolicyDocument policyDocument, String transactionId) {
        log.info("Inside generateDocument");
        policyDocument.setDocumentId(UUID.randomUUID().toString());
        policyDocument.setTransactionId(transactionId);
        TransactionDto transactionDto = transactionFeignClient.getTransactionById(transactionId).getBody();
        log.info("Transaction Details: {}", transactionDto);
        assert transactionDto != null;
        policyDocument.setPolicySelectionId(transactionDto.getPolicySelectionId());
        policyDocumentRepository.save(policyDocument);
        if (transactionDto.getStatus().equals(PaymentStatus.Successful)) {
            DocumentResponseDto documentResponseDto = DocumentResponseDto.builder()
                    .documentId(policyDocument.getDocumentId())
                    .build();
            EmailRequest emailRequest = EmailRequest.builder()
                    .emailFrom(PolicyDocumentConstants.from)
                    .emailTo(Objects.requireNonNull(selectionFeignClient.getSelectedDetailsById(policyDocument.getPolicySelectionId()).getBody()).getPolicySelectedDetails().getEmail())
                    .emailSubject(PolicyDocumentConstants.emailSubject)
                    .emailBody(getPolicyDocumentById(documentResponseDto.getDocumentId()).getDocumentId() +
                            getPolicyDocumentById(documentResponseDto.getDocumentId()).getPolicySelectedDetails() +
                            getPolicyDocumentById(documentResponseDto.getDocumentId()).getTransactionDetails())
                    .build();
            emailFeignClient.createEmail(emailRequest, documentResponseDto.getDocumentId());
            return documentResponseDto;
        } else {
            throw new GlobalExceptionHandler("Cannot access this document");
        }

    }

    @Override
    public PolicyDocumentDto getPolicyDocumentById(String documentId) {
        log.info("Inside getPolicyDocumentById");
        return mapToDto(policyDocumentRepository.findById(documentId).orElseThrow(() -> new GlobalExceptionHandler(
                String.format("Policy document not found with the id: %s", documentId)
        )));
    }

    @Override
    public List<PolicyDocumentDto> getAll() {
        log.info("Inside getAll");
        return policyDocumentRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private PolicyDocumentDto mapToDto(PolicyDocument policyDocument) {
        return PolicyDocumentDto.builder()
                .documentId(policyDocument.getDocumentId())
                .policySelectedDetails(Objects.requireNonNull(selectionFeignClient.getSelectedDetailsById(policyDocument.getPolicySelectionId()).getBody()).getPolicySelectedDetails())
                .transactionDetails(transactionFeignClient.getTransactionById(policyDocument.getTransactionId()).getBody())
                .build();
    }
}

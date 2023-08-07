package com.example.policygenerationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PolicyDocumentDto {
    private String documentId;
    private PolicySelectionDto policySelectedDetails;
    private TransactionDto transactionDetails;
}

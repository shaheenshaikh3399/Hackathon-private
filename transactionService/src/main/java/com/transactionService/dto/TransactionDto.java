package com.transactionService.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.transactionService.entities.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDto {
    private String transactionId;
    private PaymentStatus status;
    @JsonIgnore
    private String policyId;
    private String policySelectionId;
    private Double totalAmount;
}

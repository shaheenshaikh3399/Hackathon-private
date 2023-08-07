package com.example.policygenerationservice.dto;

import com.example.policygenerationservice.entity.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private String policyId;
    private PaymentStatus status;
    private String policySelectionId;
}

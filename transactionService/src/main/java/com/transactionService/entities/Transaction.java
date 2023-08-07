package com.transactionService.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction {
    @Id
    private String transactionId;
    private String policyId;
    private String policySelectionId;
    private PaymentType transactionType;
    private Double totalAmount;
    private PaymentStatus status;
}

package com.transactionService.service;

import com.transactionService.dto.TransactionDto;
import com.transactionService.dto.TransactionResponseDto;
import com.transactionService.entities.Transaction;

public interface TransactionService {
    TransactionResponseDto makePayment(String selectionId, Transaction transaction);
    TransactionDto getById(String transactionId);
}

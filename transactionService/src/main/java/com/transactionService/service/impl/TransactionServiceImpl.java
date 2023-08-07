package com.transactionService.service.impl;

import com.transactionService.dto.TransactionDto;
import com.transactionService.dto.TransactionResponseDto;
import com.transactionService.entities.PaymentStatus;
import com.transactionService.entities.Transaction;
import com.transactionService.exception.GlobalExceptionHandler;
import com.transactionService.openfeign.SelectionFeignClient;
import com.transactionService.repositories.TransactionRepo;
import com.transactionService.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepo transactionRepo;
    private final SelectionFeignClient selectionFeignClient;
    @Override
    public TransactionResponseDto makePayment(String selectionId, Transaction transaction) {
        log.info("Inside makePayment");
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setPolicySelectionId(selectionId);
        transaction.setTotalAmount(Objects.requireNonNull(Objects.requireNonNull(selectionFeignClient.getSelectedDetailsById(selectionId)).getBody()).getPolicySelectedDetails().getPolicyDetails().getAmount());
        transaction.setPolicyId(Objects.requireNonNull(selectionFeignClient.getSelectedDetailsById(selectionId).getBody()).getPolicySelectedDetails().getPolicyDetails().getPolicyId());
        transactionRepo.save(transaction);
        if(transaction.getStatus().equals(PaymentStatus.Successful)) {
            return TransactionResponseDto.builder()
                    .paymentStatus(PaymentStatus.Successful)
                    .build();
        } else if(transaction.getStatus().equals(PaymentStatus.Unsuccessful)) {
            throw new GlobalExceptionHandler("Payment is unsuccessful, please try again!!");
        } else {
            throw new GlobalExceptionHandler("Payment gateway not available!!");
        }
    }

    @Override
    public TransactionDto getById(String transactionId) {
        log.info("Inside getById");
        Transaction transaction = transactionRepo.findById(transactionId).orElseThrow(()-> new GlobalExceptionHandler(
                String.format("Policy document not found with the id: %s", transactionId)
        ));
        return mapToDto(transaction);
    }

    private TransactionDto mapToDto(Transaction transaction) {
        return TransactionDto.builder()
                .transactionId(transaction.getTransactionId())
                .status(transaction.getStatus())
                .totalAmount(transaction.getTotalAmount())
                .policySelectionId(transaction.getPolicySelectionId())
                .policyId(transaction.getPolicyId())
                .build();
    }
}

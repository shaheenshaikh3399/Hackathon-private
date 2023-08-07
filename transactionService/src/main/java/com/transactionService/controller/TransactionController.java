package com.transactionService.controller;

import com.transactionService.dto.TransactionDto;
import com.transactionService.dto.TransactionResponseDto;
import com.transactionService.entities.PaymentStatus;
import com.transactionService.entities.Transaction;
import com.transactionService.service.TransactionService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/payments")
public class TransactionController {
    private final TransactionService transactionService;
    //@CircuitBreaker(name = "makePaymentCircuitBreaker", fallbackMethod = "makePaymentFallBack")
    @PostMapping("/{id}")
    public ResponseEntity<TransactionResponseDto> makePayment(@PathVariable ("id") String selectionId, @RequestBody Transaction transaction){
        return new ResponseEntity<>(transactionService.makePayment(selectionId, transaction), HttpStatus.OK);
    }
    /*public ResponseEntity<TransactionResponseDto> makePaymentFallBack(String selectionId, Transaction transaction, Exception exception) {
        return new ResponseEntity<>(TransactionResponseDto.builder()
                .paymentStatus(PaymentStatus.PaymentGatewayDown)
                .build(), HttpStatus.BAD_REQUEST);
    }*/
    @GetMapping("/getById/{id}")
    public ResponseEntity<TransactionDto> getTransactionById(@PathVariable ("id") String transactionId){
        return new ResponseEntity<>(transactionService.getById(transactionId), HttpStatus.OK);
    }
}

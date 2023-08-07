package com.example.policygenerationservice.feignservice;

import com.example.policygenerationservice.dto.TransactionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "POLICY-TRANSACTION-SERVICE", path="/payments")
public interface TransactionFeignClient {
    @GetMapping("/getById/{id}")
    public ResponseEntity<TransactionDto> getTransactionById(@PathVariable("id") String transactionId);
}

package com.transactionService.repositories;

import com.transactionService.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepo extends JpaRepository<Transaction,String> {
}

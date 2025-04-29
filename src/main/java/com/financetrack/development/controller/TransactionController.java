package com.financetrack.development.controller;

import org.springframework.web.bind.annotation.RestController;

import com.financetrack.development.Repository.UserRepository;
import com.financetrack.development.dto.ApiResponse;
import com.financetrack.development.model.Transaction;
import com.financetrack.development.model.User;
import com.financetrack.development.service.TransactionService;

import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/get-transactions")
public ResponseEntity<ApiResponse<List<Transaction>>> getTransactions(HttpServletRequest request) {
    UUID userId = UUID.fromString((String) request.getAttribute("userId"));

    System.out.println("userId f"+ userId);
    List<Transaction> transactions = transactionService.getAllExpenses(userId);
    ApiResponse<List<Transaction>> response = new ApiResponse<>(true, "User Data Fetched Successfully", transactions);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
}

    @PostMapping("/add-expense")
    public ResponseEntity<ApiResponse<String>> addExpense(@RequestBody Transaction data, HttpServletRequest request) {
        UUID userId = UUID.fromString((String) request.getAttribute("userId")); 
        User user = userRepository.findById(userId).get();
        data.setUser(user);
        
        System.out.println("data coming in controller"+ data);
        Transaction createExpense = transactionService.addExpense(data);
        System.out.println("data coming from service"+createExpense);
        ApiResponse<String> response = new ApiResponse<>(true, "Expense Added Successfully", null);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @GetMapping("/get-recent-transactions")
    public ResponseEntity<ApiResponse<List<Transaction>>> getMethodName(HttpServletRequest request) {
        UUID userId = UUID.fromString((String) request.getAttribute("userId"));
        List<Transaction> transactions = transactionService.getRecentTransactions(userId);
        ApiResponse<List<Transaction>> entity = new ApiResponse<>(true, "Transaction Fetched Successfully", transactions);
        return ResponseEntity.status(200).body(entity);
    }

    @DeleteMapping("/delete-transaction/{id}")
    public ResponseEntity<ApiResponse<String>> postMethodName(HttpServletRequest request, @PathVariable("id") UUID transactionId) {
        UUID userId = UUID.fromString((String) request.getAttribute("userId"));

        transactionService.deleteTransaction(userId, transactionId);

        ApiResponse<String> entity = new ApiResponse<>(true, "Transaction Deleted Successfully", null);
        return ResponseEntity.status(200).body(entity);
    }
    
    
    
}

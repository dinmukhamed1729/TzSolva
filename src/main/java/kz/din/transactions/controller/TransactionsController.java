package kz.din.transactions.controller;

import kz.din.transactions.model.dto.TransactionsDTO;
import kz.din.transactions.model.entity.Transactions;
import kz.din.transactions.service.TransactionsService;
import kz.din.transactions.service.implementation.ApiConnection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    private final TransactionsService transactionsService;
    private final ApiConnection apiConnection;

    @PostMapping
    private ResponseEntity<String> transaction(@RequestBody TransactionsDTO transactionsDTO){
        System.out.println(transactionsDTO);
        transactionsService.makeTransaction(transactionsDTO);
        return ResponseEntity.ok("ok");
    }

    @GetMapping
    private ResponseEntity<List<Transactions>> getAllLimit_exceededTransactions(@RequestParam("bank") String bank){
        System.out.println(bank);
        return ResponseEntity.ok(transactionsService.getAllLimit_exceededTransactions(bank));
    }




}

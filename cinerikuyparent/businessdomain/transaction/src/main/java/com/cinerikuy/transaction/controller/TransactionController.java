package com.cinerikuy.transaction.controller;

import com.cinerikuy.transaction.dto.TransactionProductRequest;
import com.cinerikuy.transaction.dto.TransactionTicketRequest;
import com.cinerikuy.transaction.entity.*;
import com.cinerikuy.transaction.exception.BusinessRuleException;
import com.cinerikuy.transaction.repository.TransactionRepository;
import com.cinerikuy.transaction.service.ProductDataService;
import com.cinerikuy.transaction.service.TransactionComm;
import com.cinerikuy.transaction.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionComm transactionComm;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private ProductDataService productDataService;

    @Operation(summary = "Buying tickets of specific movie.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tickets added successfully", content = @Content),
            @ApiResponse(responseCode = "412", description = "Tickets selection error", content = @Content)})
    @PostMapping("/buyTickets")
    public ResponseEntity<String> buyTickets(@RequestBody TransactionTicketRequest request) throws UnknownHostException {
        CustomerData customerData = transactionComm.getCustomerData(request.getUsername());
        CinemaData cinemaData = transactionComm.getCinemaData(request.getCinemaCode());
        MovieData movieData = transactionComm.getMovieData(request.getMovieCode());
        movieData.setMovieSchedule(request.getMovieSchedule());
        movieData.setMovieNumberOfTickets(request.getMovieNumberOfTickets());
        // SETTEO
        Transaction transaction = new Transaction();
        transaction.setCustomerData(customerData);
        transaction.setCinemaData(cinemaData);
        transaction.setMovieData(movieData);

        transaction.setPaid(false);

        // TODO .. el transaction_code lo estoy asignando así, debe cambiar por un autogenerado
        Transaction saved = transactionService.post(transaction);
        saved.setTransactionCode("TR"+saved.getId());
        Transaction saved2 = transactionRepository.save(transaction);

        return ResponseEntity.ok(saved2.getTransactionCode());
    }

    @Operation(summary = "Buying products.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products added successfully", content = @Content),
            @ApiResponse(responseCode = "412", description = "Products selection error", content = @Content)})
    @PostMapping("/buyProducts")
    public ResponseEntity<String> buyProducts(@RequestBody TransactionProductRequest request) throws UnknownHostException, BusinessRuleException {

        // if customer's last transaction is non-paid, it means it comes from the buyTickets-flux
        // ..username's data won't be set, because it already exists
        // If customer's last transaction is paid, it means we're in the buyProducts-flux
        Transaction transaction = transactionService.getLast(request.getUsername());
        boolean buyProductsFlux = false;
        // transaction: null .. buyProducts-flux
        if(transaction == null) {
            buyProductsFlux = true;
            transaction = new Transaction();
            CustomerData customerData = transactionComm.getCustomerData(request.getUsername());
            transaction.setCustomerData(customerData);
            transaction.setPaid(false);
        }
        // In any case, productData will be set
        List<ProductData> productDataList = transactionComm.getProductDataList(request);
        // TODO .. refactor this
        if(buyProductsFlux) {
            Transaction saved = transactionService.post(transaction);
            saved.setTransactionCode("TR"+saved.getId());
            Transaction saved2 = transactionRepository.save(transaction);
            //productDataList.stream().forEach(p -> { p.setTransaction(saved2));
            productDataList.stream().forEach(p -> { p.setTransaction(saved2); productDataService.saveProductData(p);});
            return ResponseEntity.ok(saved2.getTransactionCode());
        }
        Transaction saved3 = transactionRepository.save(transaction);
        productDataList.stream().forEach(p -> { p.setTransaction(saved3); productDataService.saveProductData(p);});
        return ResponseEntity.ok(saved3.getTransactionCode());
    }

}

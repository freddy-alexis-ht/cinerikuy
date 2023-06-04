package com.cinerikuy.transaction.controller;

import com.cinerikuy.transaction.dto.TransactionProductRequest;
import com.cinerikuy.transaction.dto.TransactionTicketRequest;
import com.cinerikuy.transaction.entity.*;
import com.cinerikuy.transaction.exception.BusinessRuleException;
import com.cinerikuy.transaction.repository.TransactionRepository;
import com.cinerikuy.transaction.service.BillingService;
import com.cinerikuy.transaction.service.ProductDataService;
import com.cinerikuy.transaction.service.TransactionComm;
import com.cinerikuy.transaction.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;
import java.time.LocalDateTime;
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
    @Autowired
    private BillingService billingService;

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

    @Operation(summary = "Create billing")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Billing created successfully", content = @Content),
            @ApiResponse(responseCode = "412", description = "Billing creation error", content = @Content)})
    @PostMapping("/createBilling/{transactionCode}")
    public ResponseEntity<String> createBilling(@PathVariable String transactionCode) throws UnknownHostException, BusinessRuleException {
        // Con TR01 se consulta la tabla-transaction: findByTransactionCode(transactionCode)
        Transaction transaction = transactionService.findByTransactionCode(transactionCode);
        // Si no existe: Exception
        if(transaction == null)
            throw new BusinessRuleException("ErrT002", "TransactionCode no existe.", HttpStatus.PRECONDITION_FAILED);
        if(transaction.isPaid())
            throw new BusinessRuleException("ErrT003", "Transacción ya pagada.", HttpStatus.PRECONDITION_FAILED);

        // Transaction -> mappear -> Billing (transactionCode, movieName, movieSchedule)
        Billing billing = new Billing();
        billing.setTransactionCode(transaction.getTransactionCode());
        billing.setMovieName(transaction.getMovieData().getMovieName());
        billing.setMovieSchedule(transaction.getMovieData().getMovieSchedule());
        billing.setCinemaName(transaction.getCinemaData().getCinemaName());
        billing.setDate(LocalDateTime.now());

        double totalTicketCost;
        if(transaction.getCinemaData() == null || transaction.getMovieData() == null)
            totalTicketCost = 0;
        else
            totalTicketCost = transaction.getCinemaData().getCinemaTicketPrice() * transaction.getMovieData().getMovieNumberOfTickets();
        double totalProductCost = 0;
        List<ProductData> lista = productDataService.findByTransactionId(transaction.getId());
        if(lista == null)
            totalProductCost = 0;
        else
        totalProductCost = lista.stream()
                        .mapToDouble(pd -> pd.getProductAmount()*pd.getProductPrice())
                .sum();

        billing.setTotalCost(totalTicketCost+totalProductCost);

        Billing saved = billingService.post(billing);
        if(saved != null) {
            transaction.setPaid(true);
            transactionService.post(transaction);
        }
        return ResponseEntity.ok(totalTicketCost+totalProductCost+"");
    }

    @Operation(summary = "Get transaction by transactionCode.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction returned successfully", content = @Content),
            @ApiResponse(responseCode = "412", description = "Transaction doesn't exist", content = @Content)})
    @GetMapping("/transactionCode/{transactionCode}")
    public ResponseEntity<Transaction> findByTransactionCode(@PathVariable String transactionCode) throws BusinessRuleException {
        Transaction transaction = transactionService.findByTransactionCode(transactionCode);
        if(transaction == null)
            throw new BusinessRuleException("ErrT002", "TransactionCode no existe.", HttpStatus.PRECONDITION_FAILED);
        return ResponseEntity.ok(transaction);
    }

}

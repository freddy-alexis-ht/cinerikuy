package com.cinerikuy.transaction.controller;

import com.cinerikuy.transaction.dto.TransactionResponseMapper;
import com.cinerikuy.transaction.dto.TransactionTicketRequest;
import com.cinerikuy.transaction.dto.TransactionTicketResponse;
import com.cinerikuy.transaction.entity.CinemaData;
import com.cinerikuy.transaction.entity.CustomerData;
import com.cinerikuy.transaction.entity.MovieData;
import com.cinerikuy.transaction.entity.Transaction;
import com.cinerikuy.transaction.repository.TransactionRepository;
import com.cinerikuy.transaction.service.TransactionComm;
import com.cinerikuy.transaction.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;

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
    private TransactionResponseMapper traResMapper;

    @Operation(summary = "Buying tickets of specific movie.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tickets added successfully", content = @Content),
            @ApiResponse(responseCode = "412", description = "Tickets selection error", content = @Content)})
    @PostMapping("/buyTickets")
    public ResponseEntity<TransactionTicketResponse> buyTickets(@RequestBody TransactionTicketRequest request) throws UnknownHostException {
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
        // TODO .. el cinema_code lo estoy asignando así, debe cambiar por un autogenerado
        Transaction saved = transactionService.post(transaction);
        saved.setTransactionCode("TR"+saved.getId());
        Transaction saved2 = transactionRepository.save(transaction);

        TransactionTicketResponse response = traResMapper.TransactionToTransactionTicketResponse(saved2);

        return ResponseEntity.ok(response);
    }

}

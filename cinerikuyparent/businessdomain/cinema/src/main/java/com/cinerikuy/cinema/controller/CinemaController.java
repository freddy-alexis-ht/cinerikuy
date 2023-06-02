package com.cinerikuy.cinema.controller;

import com.cinerikuy.cinema.dto.CinemaResponse;
import com.cinerikuy.cinema.dto.CinemaResponseMapper;
import com.cinerikuy.cinema.entity.Cinema;
import com.cinerikuy.cinema.exception.CinemaListException;
import com.cinerikuy.cinema.service.CinemaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;
    @Autowired
    private CinemaResponseMapper cinResMapper;

    /** ALL THE CINEMAS */
    @Operation(summary = "Get all cinemas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All cinemas returned", content = @Content),
            @ApiResponse(responseCode = "412", description = "There are no cinemas", content = @Content)})
    @GetMapping()
    public ResponseEntity<List<CinemaResponse>> findAll() throws CinemaListException {
        List<Cinema> list = cinemaService.findAll();
        if(list == null)
            throw new CinemaListException("C001", "No hay cines en la DB.", HttpStatus.PRECONDITION_FAILED);
        List<CinemaResponse> response = cinResMapper.CinemaListToCinemaResponseList(list);
        return ResponseEntity.ok(response);
    }

}

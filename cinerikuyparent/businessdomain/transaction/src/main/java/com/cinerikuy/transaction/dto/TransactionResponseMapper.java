package com.cinerikuy.transaction.dto;

import com.cinerikuy.transaction.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TransactionResponseMapper {
    @Mappings({
            @Mapping(expression = "java(getTicketsTotalPrice( source.getCinemaData().getCinemaTicketPrice()," +
                    " source.getMovieData().getMovieNumberOfTickets() ))",
                    target = "ticketsTotalPrice",
                    numberFormat = "S/ #.00")
    })
    TransactionTicketResponse TransactionToTransactionTicketResponse(Transaction source);

    default String getTicketsTotalPrice(double ticketPrice, int numberOfTickets) {
        return ticketPrice * numberOfTickets + "";
    }

}

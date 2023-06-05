package com.cinerikuy.transaction.dto;

import com.cinerikuy.transaction.entity.Billing;
import com.cinerikuy.transaction.entity.Transaction;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionResponseMapper {

    // TODO .. Aun no se ha usado. Se puede usar para devolver el sub-total tickets y productos
//    @Mappings({
//            @Mapping(expression = "java(getTicketsTotalPrice( source.getCinemaData().getCinemaTicketPrice()," +
//                    " source.getMovieData().getMovieNumberOfTickets() ))",
//                    target = "ticketsTotalPrice",
//                    numberFormat = "S/ #.00")
//    })
//    TransactionCodeResponse TransactionToTransactionCodeResponse(Transaction source);
//
//    default String getTicketsTotalPrice(double ticketPrice, int numberOfTickets) {
//        return ticketPrice * numberOfTickets + "";
//    }

    // BILLING
    @Mapping(source = "totalCost", target = "totalCost")
    TransactionBillingResponse BillingToTransactionBillingResponse(Billing source);

    List<TransactionBillingResponse> BillingListToTransactionBillingResponseList(List<Billing> source);

}

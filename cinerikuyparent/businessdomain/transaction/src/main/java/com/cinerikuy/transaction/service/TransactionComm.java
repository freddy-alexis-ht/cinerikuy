package com.cinerikuy.transaction.service;

import com.cinerikuy.transaction.dto.TransactionProductRequest;
import com.cinerikuy.transaction.entity.CinemaData;
import com.cinerikuy.transaction.entity.CustomerData;
import com.cinerikuy.transaction.entity.MovieData;
import com.cinerikuy.transaction.entity.ProductData;
import com.cinerikuy.transaction.exception.BusinessRuleException;
import com.fasterxml.jackson.databind.JsonNode;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class TransactionComm {

    private final WebClient.Builder webClientBuilder;

    public TransactionComm(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    TcpClient tcpClient = TcpClient
            .create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            .doOnConnected(connection -> {
                connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS));
                connection.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS));
            });

    public CustomerData getCustomerData(String username) throws UnknownHostException {
        CustomerData customerData = new CustomerData();
        try {
            WebClient webClient = webClientBuilder.clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                    .baseUrl("http://businessdomain-customer/customers")
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .defaultUriVariables(Collections.singletonMap("url", "http://businessdomain-customer/customers"))
                    .build();
            JsonNode json = webClient.method(HttpMethod.GET).uri("/username/"+username)
                    .retrieve().bodyToMono(JsonNode.class).block();
            customerData.setCustomerDni(json.get("dni").asText());
            customerData.setCustomerUsername(json.get("username").asText());
            String name = json.get("firstName").asText() + json.get("lastName").asText();
            customerData.setCustomerName(name);
        } catch (WebClientResponseException e) {
            HttpStatus statusCode = e.getStatusCode();
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return null;
            } else {
                throw new UnknownHostException(e.getMessage());
            }
        }
        return customerData;
    }

    public CinemaData getCinemaData(String cinemaCode) throws UnknownHostException {
        CinemaData cinemaData = new CinemaData();
        try {
            WebClient webClient = webClientBuilder.clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                    .baseUrl("http://businessdomain-cinema/cinemas")
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .defaultUriVariables(Collections.singletonMap("url", "http://businessdomain-cinema/cinemas"))
                    .build();
            JsonNode json = webClient.method(HttpMethod.GET).uri("/cinemaCode/"+cinemaCode)
                    .retrieve().bodyToMono(JsonNode.class).block();
            cinemaData.setCinemaCode(json.get("cinemaCode").asText());
            cinemaData.setCinemaName(json.get("name").asText());
            String location = json.get("address").asText() +", "+ json.get("district").asText() +", "+ json.get("city").asText();
            cinemaData.setCinemaLocation(location);
            cinemaData.setCinemaTicketPrice(json.get("ticketPrice").asDouble());
        } catch (WebClientResponseException e) {
            HttpStatus statusCode = e.getStatusCode();
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return null;
            } else {
                throw new UnknownHostException(e.getMessage());
            }
        }
        return cinemaData;
    }

    public MovieData getMovieData(String movieCode) throws UnknownHostException {
        MovieData movieData = new MovieData();
        try {
            WebClient webClient = webClientBuilder.clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                    .baseUrl("http://businessdomain-movie/movies")
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .defaultUriVariables(Collections.singletonMap("url", "http://businessdomain-movie/movies"))
                    .build();
            JsonNode json = webClient.method(HttpMethod.GET).uri("/movieCode/"+movieCode)
                    .retrieve().bodyToMono(JsonNode.class).block();
            movieData.setMovieCode(json.get("movieCode").asText());
            movieData.setMovieName(json.get("name").asText());
            movieData.setMovieLanguage(json.get("language").asText());
        } catch (WebClientResponseException e) {
            HttpStatus statusCode = e.getStatusCode();
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return null;
            } else {
                throw new UnknownHostException(e.getMessage());
            }
        }
        return movieData;
    }

    public List<ProductData> getProductDataList(TransactionProductRequest request) throws BusinessRuleException, UnknownHostException {
        List<ProductData> productDataList = new ArrayList<>();
        if (request.getMapCodeAmount() != null) {
            HashMap<String, Integer> codeAmount = request.getMapCodeAmount();
            productDataList = codeAmount.entrySet().stream()
                    .map(e -> {
                        ProductData p = new ProductData();
                        try {
                            p = this.getProductData(e.getKey());
                        } catch (UnknownHostException ex) {
                            ex.printStackTrace();
                        }
                        p.setProductAmount(e.getValue());
                        return p;
                    })
                    .collect(Collectors.toList());

            }
        return productDataList;
    }
    public ProductData getProductData(String productCode) throws UnknownHostException {
        ProductData productData = new ProductData();
        try {
            WebClient webClient = webClientBuilder.clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                    .baseUrl("http://businessdomain-product/products")
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .defaultUriVariables(Collections.singletonMap("url", "http://businessdomain-product/products"))
                    .build();
            JsonNode json = webClient.method(HttpMethod.GET).uri("/productCode/"+productCode)
                    .retrieve().bodyToMono(JsonNode.class).block();
            productData.setProductCode(json.get("productCode").asText());
            productData.setProductName(json.get("name").asText());
            productData.setProductPrice(json.get("price").asDouble());
        } catch (WebClientResponseException e) {
            HttpStatus statusCode = e.getStatusCode();
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return null;
            } else {
                throw new UnknownHostException(e.getMessage());
            }
        }
        return productData;
    }


}

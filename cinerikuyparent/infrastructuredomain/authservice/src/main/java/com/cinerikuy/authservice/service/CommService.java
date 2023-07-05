package com.cinerikuy.authservice.service;

import com.cinerikuy.authservice.dto.SignInRequest;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.net.UnknownHostException;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Service
public class CommService {

    private final WebClient.Builder webClientBuilder;

    public CommService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    TcpClient tcpClient = TcpClient
            .create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            .doOnConnected(connection -> {
                connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS));
                connection.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS));
            });

    public boolean saveCustomer(SignInRequest request) throws UnknownHostException {
        try {
            WebClient webClient = webClientBuilder.clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                    .baseUrl("http://businessdomain-customer/customers")
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .defaultUriVariables(Collections.singletonMap("url", "http://businessdomain-customer/customers"))
                    .build();

            MultiValueMap<String, String> bodyValues = new LinkedMultiValueMap<>();
            bodyValues.add("username", request.getUsername());
            bodyValues.add("firstName", request.getFirstName());
            bodyValues.add("lastName", request.getLastName());
            bodyValues.add("dni", request.getDni());
            bodyValues.add("cellphone", request.getCellphone());

            JsonNode json = webClient.method(HttpMethod.POST).uri("/signin")
//                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                    .accept(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromFormData(bodyValues))
                    .retrieve().bodyToMono(JsonNode.class).block();
        } catch (WebClientResponseException e) {
            HttpStatus statusCode = e.getStatusCode();
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return false;
            } else {
                throw new UnknownHostException(e.getMessage());
            }
        }
        return true;
    }

}

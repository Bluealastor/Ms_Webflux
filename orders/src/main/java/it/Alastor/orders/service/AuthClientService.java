package it.Alastor.orders.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthClientService {

    @Value("${auth.service.url}")
    private String authServiceUrl;

    private final WebClient.Builder webClientBuilder;

    public Mono<String> validateToken(String token){
        return webClientBuilder.build()
                .get()
                .uri(authServiceUrl)
                .header(HttpHeaders.AUTHORIZATION, token)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        response -> Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token non valido")))
                .bodyToMono(String.class);
    }


}

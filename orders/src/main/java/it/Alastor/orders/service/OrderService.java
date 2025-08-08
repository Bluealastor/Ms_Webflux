package it.Alastor.orders.service;

import it.Alastor.orders.entity.Order;
import it.Alastor.orders.exception.UnauthorizedException;
import it.Alastor.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final AuthClientService authClientService;

    public List<Order> getAllOrders(Pageable pageable){
        return orderRepository.findAll(pageable).getContent();
    }

    public Flux<Order> getAllOrdersToken(Pageable pageable, String token) {
        return authClientService.validateToken(token)
                .flatMapMany(response -> {
                    if ("valid".equalsIgnoreCase(response)) {
                        return Flux.fromIterable(orderRepository.findAll(pageable).getContent());
                    } else {
                        return Flux.error(new UnauthorizedException("401", "Token non valido"));
                    }
                });
    }

}

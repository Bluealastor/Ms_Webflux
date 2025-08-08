package it.Alastor.orders.controller;

import it.Alastor.orders.entity.Order;
import it.Alastor.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(@RequestHeader("Authorization") String token, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(orderService.getAllOrders(PageRequest.of(page, size)));
    }

    @GetMapping
    public Flux<Order> getAllOrdersToken(
            @RequestHeader("Authorization") String token,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        // Qui puoi anche verificare il token e lanciare eccezioni
        return orderService.getAllOrdersToken(PageRequest.of(page, size), token);
    }

}

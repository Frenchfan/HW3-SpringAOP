package com.sumkin.hw3.controllers;

import com.sumkin.hw3.model.Order;
import com.sumkin.hw3.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
@Tag(name = "Заказы")
@RequiredArgsConstructor
@Log4j2
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @Operation(summary = "Create Order")
    public Order createOrder(@RequestParam("userId") Long userId, @RequestBody Order order) {
        return orderService.createOrder(order, userId);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить заказ")
    public void deleteOrder(@PathVariable("id") Long id) {
        log.info("trying to delete order with id = " + id);
        orderService.deleteOrder(id);
    }

    @GetMapping
    @Operation(summary = "Получить список заказов")
    public List<Order> getOrders() {
        log.info("trying to get all orders");
        return orderService.getAllOrders();
    }


    @PutMapping("/{id}")
    @Operation(summary = "Обновить заказ")
    public Order updateOrder(@PathVariable("id") Long orderId, @RequestBody Order order) {
        {
            log.info("trying to update order with id = " + orderId);
            return orderService.updateOrder(orderId, order);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить заказ")
    public Order getOrderById(@PathVariable("id") Long orderId) {
        log.info("trying to get order with id = " + orderId);
        return orderService.getOrderById(orderId);
    }
}


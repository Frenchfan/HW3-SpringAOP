package com.sumkin.hw3.services;

import com.sumkin.hw3.exception.ResourceNotFoundException;
import com.sumkin.hw3.model.Order;
import com.sumkin.hw3.model.Status;
import com.sumkin.hw3.model.User;
import com.sumkin.hw3.repositories.OrderRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Setter
@Getter
@Log4j2
public class OrderService {

    private final OrderRepository orderRepository;

    private final UserService userService;

    @Transactional
    public Order createOrder(Order order, Long userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new ResourceNotFoundException("No such user to associate the order with");
        }

        order.setUser(user);
        order.setStatus(Status.CREATED);
        return orderRepository.save(order);
    }
    @Transactional
    public void deleteOrder(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new ResourceNotFoundException("No such order found");
        }        orderRepository.deleteById(orderId);
        log.info("Order with id = " + orderId + " deleted successfully");
    }

    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        if (orders.isEmpty()) {
            throw new ResourceNotFoundException("No orders found");
        }
        log.info(orders.size() + " orders found");
        return orders;
    }

    @Transactional
    public Order updateOrder(Long orderId, Order updatedOrder) {
        Optional<Order> existingOrderOptional = orderRepository.findById(orderId);
        if (existingOrderOptional.isPresent()) {
            Order existingOrder = existingOrderOptional.get();
            existingOrder.setDescription(updatedOrder.getDescription());
            existingOrder.setStatus(updatedOrder.getStatus());
            log.info("Order with id = " + orderId + " updated successfully");
            return orderRepository.save(existingOrder);
        } else {
            throw new ResourceNotFoundException("No such order found");
        }
    }


    @Transactional(readOnly = true)
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("No such order found"));
    }
}

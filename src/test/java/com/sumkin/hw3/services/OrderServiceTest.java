package com.sumkin.hw3.services;


import com.sumkin.hw3.model.Order;
import com.sumkin.hw3.model.Status;
import com.sumkin.hw3.model.User;
import com.sumkin.hw3.repositories.OrderRepository;
import com.sumkin.hw3.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



@SpringBootTest
public class OrderServiceTest {

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testCreateOrder() {
        User user = new User(1L,"Alice", "alice@example.com");
        when(userRepository.save(user)).thenReturn(user);

        User createdUser = userService.createUser(user);


        Order order = new Order();
        order.setUser(user);
        order.setStatus(Status.CREATED);
        when(orderRepository.save(order)).thenReturn(order);


        assertNotNull(createdUser);
        assertEquals(user.getName(), createdUser.getName());
        assertEquals(user.getEmail(), createdUser.getEmail());
    }

    @Test
    public void testDeleteOrder() {
        Long orderId = 1L;
        when(orderRepository.existsById(orderId)).thenReturn(true);

        orderService.deleteOrder(orderId);

        verify(orderRepository, times(1)).deleteById(orderId);
    }

    @Test
    public void testGetAllOrders() {
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order());
        orderList.add(new Order());
        when(orderRepository.findAll()).thenReturn(orderList);

        List<Order> orders = orderService.getAllOrders();

        assertFalse(orders.isEmpty());
        assertEquals(2, orders.size());
    }

    @Test
    public void testUpdateOrder() {
        Long orderId = 1L;
        long userId = 1L;
        User existingUser = new User(userId, "Alice", "alice@example.com");

        Order order = new Order("Initial description");
        Order updatedOrder = new Order("Updated description");
        order.setId(orderId);
        order.setUser(existingUser);
        order.setStatus(Status.CREATED);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order result = orderService.updateOrder(orderId, updatedOrder);

        assertEquals(updatedOrder.getDescription(), result.getDescription());
        assertEquals(updatedOrder.getStatus(), result.getStatus());
    }

    @Test
    public void testGetOrderById() {
        Long orderId = 1L;
        Order order = new Order();
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        Order retrievedOrder = orderService.getOrderById(orderId);

        assertNotNull(retrievedOrder);
        assertEquals(order, retrievedOrder);
    }
}
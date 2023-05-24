package com.abhinav.orderservice.controller;

import com.abhinav.base.domains.dto.Order;
import com.abhinav.base.domains.dto.OrderEvent;
import com.abhinav.orderservice.kafka.OrderProducer;
import com.abhinav.orderservice.status.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
    private final OrderProducer orderProducer;
    private final Logger log = LoggerFactory.getLogger(OrderController.class);
    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping("/orders")
    public String placeOrder(@RequestBody Order order) {
        try{
            log.info("Request received for /orders : " + "data in request body is " + order.toString());
            order.setOrderId(UUID.randomUUID().toString());
            OrderEvent orderEvent = new OrderEvent();
            orderEvent.setOrderStatus(String.valueOf(OrderStatus.PENDING));
            orderEvent.setMessage("Order status is in pending state");
            orderEvent.setOrder(order);

            orderProducer.sendMessage(orderEvent);
            return "Order placed successfully";
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

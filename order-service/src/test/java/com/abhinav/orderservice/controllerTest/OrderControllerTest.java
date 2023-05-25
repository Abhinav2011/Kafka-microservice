package com.abhinav.orderservice.controllerTest;

import com.abhinav.base.domains.dto.Order;
import com.abhinav.base.domains.dto.OrderEvent;
import com.abhinav.orderservice.config.KafkaConfig;
import com.abhinav.orderservice.controller.OrderController;
import com.abhinav.orderservice.kafka.OrderProducer;
import com.abhinav.orderservice.status.OrderStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.UUID;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
@Import({OrderController.class, KafkaConfig.class})
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderProducer orderProducer;
    private final Logger log = LoggerFactory.getLogger(OrderControllerTest.class);
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    private Order createSampleOrder() {
        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString());
        order.setName("MacBook Pro");
        order.setQuantity(1);
        order.setPrice(1000.0);
        return order;
    }

    @Test
    public void placeOrderTest() throws Exception{
        Order order = createSampleOrder();
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setOrderStatus(String.valueOf(OrderStatus.PENDING));
        orderEvent.setMessage("Order status is in pending state");
        orderEvent.setOrder(order);

        doNothing().when(orderProducer).sendMessage(orderEvent);
//        verify(orderProducer,times(1)).sendMessage(orderEvent);
        log.info("Order place is " + order.toString());
        mockMvc.perform(post("/api/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(order)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Order placed successfully"));
    }

}

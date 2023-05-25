package com.abhinav.emailservice.kafka;

import com.abhinav.base.domains.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {
    private static final Logger log = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(topics = "order_topics", groupId = "stock")
    public void consume(OrderEvent orderEvent) {
        log.info("Order consumed (Order event from order service) from kafka topic order_topics is " + orderEvent.toString());
        //further save this orderEvent data to database for further processing
    }
}

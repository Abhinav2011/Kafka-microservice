package com.abhinav.orderservice.kafka;

import com.abhinav.base.domains.dto.OrderEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
public class OrderProducer {
    private final NewTopic newTopic;
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public OrderProducer(NewTopic newTopic, KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.newTopic = newTopic;
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendMessage(OrderEvent orderEvent) {
        //create message
        Message<OrderEvent> orderEventMessage = MessageBuilder.withPayload(orderEvent).setHeader(KafkaHeaders.TOPIC, newTopic.name()).build();
        //send message
        kafkaTemplate.send(orderEventMessage);
    }
}

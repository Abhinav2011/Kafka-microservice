package com.abhinav.orderservice.kafka;

import com.abhinav.base.domains.dto.OrderEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private NewTopic newTopic;

    private KafkaTemplate<String, OrderEvent> kafkaTemplate;


    public OrderProducer(NewTopic newTopic, KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.newTopic = newTopic;
        this.kafkaTemplate = kafkaTemplate;
    }
    public OrderProducer() {}
    public void sendMessage(OrderEvent orderEvent) {
        //create message
        Message<OrderEvent> orderEventMessage = MessageBuilder.withPayload(orderEvent).setHeader(KafkaHeaders.TOPIC, newTopic.name()).build();
        //send message
        kafkaTemplate.send(orderEventMessage);
    }
}

# Kafka-microservice
SpringBoot microservice project using kafka. Project is made for learning kafka

## High Level Diagram
![high level diagram](https://github.com/Abhinav2011/Kafka-microservice/assets/62784600/0e8b904e-0074-4528-812b-7a2272d392ef)

### Order service
Order service publishes an order event to the kafka broker to the topic order_topics. It acts as the publisher.

### Email and Stock service
Both of these services subscribes to the kafka broker to listen for messages published in the kafka broker on the topic order_topics. They act as the consumer. 

#### Note
Since this project is for learning about kafka. The events consumed are not utilised further. But this data consumed is processed further according to the needs of our application. 

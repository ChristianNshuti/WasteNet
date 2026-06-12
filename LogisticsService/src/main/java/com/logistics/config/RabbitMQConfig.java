package com.logistics.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String PICKUP_REQUESTED_QUEUE = "pickup.requested.queue";
    public static final String PICKUP_COMPLETED_QUEUE = "pickup.completed.queue";
    public static final String EXCHANGE = "waste.management.exchange";
    public static final String PICKUP_REQUESTED_ROUTING_KEY = "pickup.requested";
    public static final String PICKUP_COMPLETED_ROUTING_KEY = "pickup.completed";

    @Bean
    public Queue pickupRequestedQueue() {
        return new Queue(PICKUP_REQUESTED_QUEUE, true);
    }

    @Bean
    public Queue pickupCompletedQueue() {
        return new Queue(PICKUP_COMPLETED_QUEUE, true);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding pickupRequestedBinding(Queue pickupRequestedQueue, TopicExchange exchange) {
        return BindingBuilder.bind(pickupRequestedQueue).to(exchange).with(PICKUP_REQUESTED_ROUTING_KEY);
    }

    @Bean
    public Binding pickupCompletedBinding(Queue pickupCompletedQueue, TopicExchange exchange) {
        return BindingBuilder.bind(pickupCompletedQueue).to(exchange).with(PICKUP_COMPLETED_ROUTING_KEY);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(converter());
        return template;
    }
}

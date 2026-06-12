package com.FacilitiesService.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String PICKUP_COMPLETED_QUEUE = "pickup.completed";
    public static final String EXCHANGE_NAME = "pickup.exchange";
    public static final String PICKUP_COMPLETED_ROUTING_KEY = "pickup.completed";

    @Bean
    public Queue pickupCompletedQueue() {
        return new Queue(PICKUP_COMPLETED_QUEUE, true);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding pickupCompletedBinding(Queue pickupCompletedQueue, DirectExchange exchange) {
        return BindingBuilder.bind(pickupCompletedQueue).to(exchange).with(PICKUP_COMPLETED_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}

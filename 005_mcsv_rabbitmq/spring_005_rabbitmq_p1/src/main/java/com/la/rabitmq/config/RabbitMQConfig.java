package com.la.rabitmq.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	public static final String EXCHANGE_STRING = "message_exchange";
	public static final String ROUTING_KEY_COLOR = "color_routing_key";
	public static final String ROUTING_KEY_SHAPE = "shape_routing_key";

	@Bean
	public AmqpTemplate amqpTemplate(ConnectionFactory connection) {
		RabbitTemplate template = new RabbitTemplate(connection);
		template.setMessageConverter(messageConverter());
		return template;
	}

	@Bean
	public MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public TopicExchange topicExchange() {
		return new TopicExchange(EXCHANGE_STRING);
	}

}

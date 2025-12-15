package com.la.rabitmq.consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.la.rabitmq.consumer.config.RabbitMQConfig;
import com.la.rabitmq.consumer.model.Models;

@Service
public class ConsumerListener {

	private static final Logger log = LoggerFactory.getLogger(ConsumerListener.class);

	@RabbitListener(queues = RabbitMQConfig.QUEUE_COLOR)
	public void readColorMsg(Models.Color color) {
		log.info("Receiving color: {}", color);
	}

	@RabbitListener(queues = RabbitMQConfig.QUEUE_SHAPE)
	public void readColorMsg(Models.SHape color) {
		log.info("Receiving shape: {}", color);
	}

}

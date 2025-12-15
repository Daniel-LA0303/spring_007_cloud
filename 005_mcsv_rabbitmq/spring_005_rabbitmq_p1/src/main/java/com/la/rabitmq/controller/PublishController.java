package com.la.rabitmq.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.la.rabitmq.config.RabbitMQConfig;
import com.la.rabitmq.model.Models;

@RestController
@RequestMapping(path = "publish")
public class PublishController {

	@Autowired
	private RabbitTemplate template;

	@PostMapping(path = "color")
	public String publishColor(@RequestBody Models.Color color) {

		this.template.convertAndSend(RabbitMQConfig.EXCHANGE_STRING, RabbitMQConfig.ROUTING_KEY_COLOR, color);
		return "Color msg send succes";

	}

	@PostMapping(path = "shape")
	public String publishColor(@RequestBody Models.SHape shape) {

		this.template.convertAndSend(RabbitMQConfig.EXCHANGE_STRING, RabbitMQConfig.ROUTING_KEY_COLOR, shape);
		return "Shape msg send succes";

	}

}

package com.example.SpringRabbitConsumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
	
	@RabbitListener(queues = MQConfig.QUEUE_MSG)
	public void listenMessage(CustomMessage msg) {
		System.out.println("Message is :: "+ msg);
	}

}

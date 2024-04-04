package com.example.SpringRabbitConsumer;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//Queue name, Topic Exchange, Bind , MessageTemplate, RabbitTemplate
@Configuration
public class MQConfig {

	public static final String QUEUE_MSG ="MESSAGE_QUEUE";
	public static final String QUEUE_EXCHANGE ="Message Exchanged..";
	public static final String QUEUE_ROUTING ="Message Routing key";
	
	@Bean
	public Queue queue() {
		return new Queue(QUEUE_MSG);
	}
	
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(QUEUE_EXCHANGE);
	}
	
	@Bean
	public Binding bind(Queue queue, TopicExchange exchange) {
		return BindingBuilder.
				bind(queue)
				.to(exchange)
				.with(QUEUE_ROUTING);
	}
	
	@Bean
	public MessageConverter messageConvert() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public AmqpTemplate template(ConnectionFactory connectionFactory) {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setMessageConverter(messageConvert());
		return template;
		
	}
	
	
}

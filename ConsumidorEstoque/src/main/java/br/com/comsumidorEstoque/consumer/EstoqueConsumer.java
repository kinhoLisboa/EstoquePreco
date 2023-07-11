package br.com.comsumidorEstoque.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import libbRabbitmq.constantes.RabbitMQConstantes;
import libbRabbitmq.dto.EstoqueDto;

@Component
public class EstoqueConsumer {
	
	@RabbitListener(queues = RabbitMQConstantes.FILA_ESTOQUE)
	private void consumidor( EstoqueDto estoque) {
		
		System.out.println(estoque.codigoProduto);
		System.out.println(estoque.quantidade);
		System.out.println("------------------------");
		
	}
}

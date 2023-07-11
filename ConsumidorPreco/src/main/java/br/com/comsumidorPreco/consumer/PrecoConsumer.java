package br.com.comsumidorPreco.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import libbRabbitmq.constantes.RabbitMQConstantes;
import libbRabbitmq.dto.PrecoDto;

@Component
public class PrecoConsumer {
	
	@RabbitListener(queues = RabbitMQConstantes.FILA_PRECO)
	public void consumidor(PrecoDto precoDto) {
		
		System.out.println(precoDto.codigoProduto);
		System.out.println(precoDto.preco);
		System.out.println("------------------------");
		
		
	}

}

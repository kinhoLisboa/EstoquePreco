package br.com.EstoquePreco.conections;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import libbRabbitmq.constantes.RabbitMQConstantes;

@Component
public class RabbitMqConections {
	
	@Autowired
	private AmqpAdmin amqpAdmin;
	private static final String NOME_EXCHAGE = "amq.direct";
	
	public RabbitMqConections(AmqpAdmin amqpAdmin) {
		this.amqpAdmin=  amqpAdmin;
	}
	
	private Queue fila(String nomeFila) {
		return new Queue (nomeFila, true, false, false);
	}
	
	private DirectExchange trocaDireta() {
		return new DirectExchange(NOME_EXCHAGE);
	}
	private Binding  relacionamento (Queue fila, DirectExchange troca) {
		return new Binding (fila.getName(),Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null );
	}
	@PostConstruct
	public void adiciona() {
	Queue filaEstoque =	this.fila(RabbitMQConstantes.FILA_ESTOQUE);
	Queue filaPreco =	this.fila(RabbitMQConstantes.FILA_PRECO);
	
	DirectExchange troca= this.trocaDireta();
	Binding ligacaoEstoque = this.relacionamento(filaEstoque, troca);
	Binding ligacaoPreco = this.relacionamento(filaPreco, troca);
	
	this.amqpAdmin.declareQueue(filaEstoque);
	this.amqpAdmin.declareQueue(filaPreco);
	
	this.amqpAdmin.declareExchange(troca);
	this.amqpAdmin.declareBinding(ligacaoEstoque);
	this.amqpAdmin.declareBinding(ligacaoPreco);
	}
}	

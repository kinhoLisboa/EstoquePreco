package br.com.EstoquePreco.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.EstoquePreco.service.RabbitMQService;
import libbRabbitmq.constantes.RabbitMQConstantes;
import libbRabbitmq.dto.EstoqueDto;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
	@Autowired
	private RabbitMQService service;
	
	@PutMapping
	private ResponseEntity alteraEstoque(@RequestBody EstoqueDto estoqueDto) {
		service.enviaMensagem(RabbitMQConstantes.FILA_ESTOQUE, estoqueDto);
		return new ResponseEntity(HttpStatus.OK);
	}

}

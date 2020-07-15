package com.gerenciadordepedido.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciadordepedido.model.Cliente;
import com.gerenciadordepedido.model.PedidoDTO;
import com.gerenciadordepedido.repository.PedidoRepository;
import com.gerenciadordepedido.service.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping
	public List<PedidoDTO> list() {
		return pedidoService.listarPedidos();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoDTO save(@RequestBody PedidoDTO pedido) {
		return pedidoService.save(pedido);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/cliente/{cpf}")
	public ResponseEntity<Cliente> buscarClienteId(@PathVariable String cpf) {
		Optional<Cliente> cliente = pedidoRepository.findClienteByCpf(cpf);
		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/produto/{sku}")
	public PedidoDTO buscarPedidoProduto(@PathVariable String sku) {
		return pedidoService.buscarPedidoProduto(sku);		
	}
}

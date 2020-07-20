package com.gerenciadordepedido.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciadordepedido.model.Cliente;
import com.gerenciadordepedido.model.Pedido;
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
	public Pedido save(@RequestBody PedidoDTO pedido) {
		return pedidoService.save(pedido);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/cliente/{cpf}")
	public ResponseEntity<Cliente> buscarClienteId(@PathVariable String cpf) {
		Optional<Cliente> cliente = pedidoRepository.findClienteByCpf(cpf);
		if (!cliente.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cliente.get());
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		if (!pedidoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		pedidoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/produto/{sku}")
	public PedidoDTO buscarPedidoProduto(@PathVariable String sku) {
		return pedidoService.buscarPedidoProduto(sku);		
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/{id}")
	public ResponseEntity<PedidoDTO> update(@RequestBody PedidoDTO pedido, @PathVariable Integer id) {
		if (!pedidoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		pedidoRepository.deleteById(id);
		pedidoService.save(pedido);
		return ResponseEntity.ok(pedido);
	}
}

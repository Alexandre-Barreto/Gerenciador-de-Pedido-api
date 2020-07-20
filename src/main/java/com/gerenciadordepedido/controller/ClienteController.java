package com.gerenciadordepedido.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import com.gerenciadordepedido.repository.ClienteRepository;
import com.gerenciadordepedido.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteService clienteService;
	

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Cliente> save(@Validated @RequestBody Cliente cliente) {
		Optional<Cliente> clienteByCPF = clienteRepository.clienteExistByCpf(cliente.getCpf());
		if(!clienteByCPF.isPresent()) {
			return ResponseEntity.badRequest().build();
		}	
		clienteRepository.save(cliente);
		return ResponseEntity.ok(cliente);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping
	public List<Cliente> list() {
		return clienteRepository.findAll();
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		if (!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		clienteService.delete(id);
		return ResponseEntity.noContent().build();

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> update(@Validated @RequestBody Cliente cliente, @PathVariable Integer id) {
	if (!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
	}
	
	if(clienteRepository.clienteExistByCpf(cliente.getCpf()) != null) {
		return ResponseEntity.badRequest().build();
	}	
		cliente.setId(id);
		clienteRepository.save(cliente);
		return ResponseEntity.ok(cliente);
	}
}

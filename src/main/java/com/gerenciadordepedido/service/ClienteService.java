package com.gerenciadordepedido.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciadordepedido.model.Pedido;
import com.gerenciadordepedido.repository.ClienteRepository;
import com.gerenciadordepedido.repository.PedidoRepository;

@Service
public class ClienteService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public void delete(Integer id) {
		List<Pedido> pedidos = pedidoRepository.findAllByClienteID(id);
		for (int i = 0; i < pedidos.size(); i++) {
			pedidoRepository.deleteById(pedidos.get(i).getId());
		}
		clienteRepository.deleteById(id);
	}
}

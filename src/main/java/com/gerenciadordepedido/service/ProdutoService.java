package com.gerenciadordepedido.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciadordepedido.model.Pedido;
import com.gerenciadordepedido.repository.PedidoRepository;
import com.gerenciadordepedido.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public void delete(Integer id) {
		List<Pedido> pedidos = pedidoRepository.findAllByProdutoID(id);
		for (int i = 0; i < pedidos.size(); i++) {
			pedidoRepository.deleteById(pedidos.get(i).getId());
		}
		produtoRepository.deleteById(id);
	}	

}

package com.gerenciadordepedido.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciadordepedido.model.Cliente;
import com.gerenciadordepedido.model.Pedido;
import com.gerenciadordepedido.model.PedidoDTO;
import com.gerenciadordepedido.model.Produto;
import com.gerenciadordepedido.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;	
	
	public List<PedidoDTO> listarPedidos(){
		List<Pedido> pedidos = pedidoRepository.findAll();
		List<PedidoDTO> listaPedidos = new ArrayList<PedidoDTO>();
		Pedido pedido = new Pedido();

		for (int i = 0; i < pedidos.size(); i++) {
			PedidoDTO pedidoDTO = new PedidoDTO();
			pedido = pedidos.get(i);
			pedidoDTO.setCliente(pedidoRepository.findClienteById(pedido.getClienteID()));
			pedidoDTO.setProdutos(pedidoRepository.findProdutoById(pedido.getProdutoID()));
			pedidoDTO.setDataCompra(pedido.getDataDaCompra().toString().substring(0, 11));			
			pedidoDTO.setTotalCompra(pedido.getTotalDaCompra());
			pedidoDTO.setId(pedido.getId());
			
			listaPedidos.add(pedidoDTO);
		}
		return listaPedidos;
	}

	public Cliente save(Pedido pedido) {
		
		return null;
	}

	public PedidoDTO buscarPedidoProduto(String sku) {
		List <Produto> produtos = new ArrayList<Produto>();
		PedidoDTO pedido = new PedidoDTO();
		String[] novoSku = sku.split(",");		
		
		for (int i = 0; i < novoSku.length; i++) {
			produtos.add(pedidoRepository.findProdutoBySku(sku));
		}
		pedido.setProdutos(produtos);
		Integer preco = Integer.parseInt(produtos.get(0).getPreco());
		
		for (int i = 1; i < produtos.size(); i++) {
			int valor =Integer.parseInt(produtos.get(i).getPreco());
			String valorFinal = Integer.toString(preco+valor);
			pedido.setTotalCompra(valorFinal);
		}
		
		return pedido;
	}

}

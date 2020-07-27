package com.gerenciadordepedido.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		List<PedidoDTO> listaPedidos = new ArrayList<>();
		Pedido pedido;

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

	public Pedido save(PedidoDTO pedidoDTO) {
		Pedido pedido = new Pedido();
		Date data = new Date(System.currentTimeMillis());
		for (int i = 0; i < pedidoDTO.getProdutos().size(); i++) {
			pedido.setDataDaCompra(data);
			pedido.setClienteID(pedidoDTO.getCliente().getId());
			pedido.setTotalDaCompra(pedidoDTO.getTotalCompra());
			pedido.setProdutoID(pedidoDTO.getProdutos().get(i).getId());
			pedidoRepository.save(pedido);
		}
		
		return pedido;
	}

	public PedidoDTO buscarPedidoProduto(String sku) {
		List <Produto> produtos = new ArrayList<>();
		PedidoDTO pedido = new PedidoDTO();
		String[] novoSku = sku.split(",");		
		
		for (int i = 0; i < novoSku.length; i++) {
			produtos.add(pedidoRepository.findProdutoBySku(novoSku[i]));
		}
		pedido.setProdutos(produtos);
		Integer preco = this.stringToInt(produtos.get(0).getPreco());
		
		if (produtos.size() == 1) {
			pedido.setTotalCompra(this.intToString(preco));
			return pedido;
		}
		
		for (int i = 1; i < produtos.size(); i++) {
			String valorFinal = intToString(preco+ this.stringToInt(produtos.get(i).getPreco()));
			pedido.setTotalCompra(valorFinal);
		}		
		return pedido;
	}
	
	private Integer stringToInt(String numero) {
		numero = numero.replaceAll("[^0-9]", "");
		return Integer.parseInt(numero);
	}
	
	private String intToString(Integer numero) {
		String valor = Integer.toString(numero);
		StringBuilder sb = new StringBuilder(valor);
        for (int i = sb.length() -3; i > 0; i -= 3) {
			sb.insert(i, ".");
		}
        sb.insert(sb.length(), ",00");
		return sb.toString();
	}

}

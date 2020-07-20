package com.gerenciadordepedido.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gerenciadordepedido.model.Cliente;
import com.gerenciadordepedido.model.Pedido;
import com.gerenciadordepedido.model.Produto;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Integer>{
	
	@Query("SELECT c FROM Cliente c WHERE c.id = :id")
	public Cliente findClienteById(Integer id);
	
	@Query("SELECT pr FROM Produto pr WHERE pr.id = :id")
	public List<Produto> findProdutoById(Integer id);
	
	@Query("SELECT c FROM Cliente c WHERE c.cpf = :cpf")
	public Optional<Cliente> findClienteByCpf(String cpf);
	
	@Query("SELECT p FROM Produto p WHERE p.sku = :sku")
	public Produto findProdutoBySku(String sku);
	
	@Query("SELECT pc FROM Pedido pc WHERE pc.clienteID = :id")
	public List<Pedido> findAllByClienteID(Integer id);
	
	@Query("SELECT pp FROM Pedido pp WHERE pp.produtoID = :id")
	public List<Pedido> findAllByProdutoID(Integer id);
	
}

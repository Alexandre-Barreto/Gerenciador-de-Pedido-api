package com.gerenciadordepedido.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gerenciadordepedido.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer>{
	
	@Query("SELECT c FROM Cliente c WHERE c.cpf = :cpf")
	public Optional<Cliente> clienteExistByCpf(String cpf);
		
}

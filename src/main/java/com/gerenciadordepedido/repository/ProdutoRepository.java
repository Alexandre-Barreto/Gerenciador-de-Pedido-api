package com.gerenciadordepedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gerenciadordepedido.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Integer> {

}

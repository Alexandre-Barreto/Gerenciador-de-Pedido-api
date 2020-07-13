package com.gerenciadordepedido.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import com.sun.istack.NotNull;

@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pedidoID")
	private Integer id;
	
	@Column(name = "clienteID")
	private Integer clienteID;
	
	@Column(name = "produtoID")
	private Integer produtoID;
	
	@Column(name = "totalDaCompra")
	@NotNull
	private String totalDaCompra;
	
	@Column(name = "dataDaCompra")
	private Date dataDaCompra;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getClienteID() {
		return clienteID;
	}

	public void setClienteID(Integer clienteID) {
		this.clienteID = clienteID;
	}

	public Integer getProdutoID() {
		return produtoID;
	}

	public void setProdutoID(Integer produtoID) {
		this.produtoID = produtoID;
	}

	public String getTotalDaCompra() {
		return totalDaCompra;
	}

	public void setTotalDaCompra(String totalDaCompra) {
		this.totalDaCompra = totalDaCompra;
	}

	public Date getDataDaCompra() {
		return dataDaCompra;
	}

	public void setDataDaCompra(Date dataDaCompra) {
		this.dataDaCompra = dataDaCompra;
	}	
}

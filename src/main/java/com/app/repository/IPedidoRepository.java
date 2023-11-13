package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.modelos.Pedido;

public interface IPedidoRepository extends JpaRepository<Pedido, Integer>{

}

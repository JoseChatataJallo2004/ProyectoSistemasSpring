package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.modelos.Producto;

public interface IProductoRepository extends JpaRepository<Producto, Integer> {

	Producto findByidproducto(Integer idproducto);
	
	List<Producto> findByEstado(int estado);

	
}

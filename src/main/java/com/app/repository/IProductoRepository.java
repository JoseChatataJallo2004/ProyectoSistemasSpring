package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.modelos.Marca;
import com.app.modelos.Producto;

public interface IProductoRepository extends JpaRepository<Producto, Integer> {

	Producto findByidproducto(Integer idproducto);
	
	
}

package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.app.modelos.Marca;
import com.app.modelos.Producto;

public interface IProductoRepository extends JpaRepository<Producto, Integer> {

	Producto findByIdproducto(Integer idproducto);
	
	List<Producto> findByEstado(int estado);
	
	List<Producto> findByIdMarca(Marca marca);

}

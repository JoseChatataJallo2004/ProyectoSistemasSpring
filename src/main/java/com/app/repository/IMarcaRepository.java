package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.modelos.Marca;


public interface IMarcaRepository extends JpaRepository<Marca, Integer>{
	Marca findByCodigo(Integer codigo);
	List<Marca> findByEstado(int estado);
}

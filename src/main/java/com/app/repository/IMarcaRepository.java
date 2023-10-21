package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.modelos.Marca;


public interface IMarcaRepository extends JpaRepository<Marca, Integer>{
	Marca findByCodigo(Integer codigo);
}

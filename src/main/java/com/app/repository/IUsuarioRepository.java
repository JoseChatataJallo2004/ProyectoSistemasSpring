package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.modelos.Usuario;


public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

	
	public Usuario findByCodigousuarioAndClave(String codigousuario, String clave);

	
}

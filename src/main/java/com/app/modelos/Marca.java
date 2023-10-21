package com.app.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="marca")
public class Marca {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idmarca")
	private int codigo;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="estado")
	private int estado;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

 //Prueba 159 jose
	
	
	
}

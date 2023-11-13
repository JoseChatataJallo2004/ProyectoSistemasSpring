package com.app.modelos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Pedidos")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idpedido")
	private int codigo;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "telefono")
	private String telefono;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "dni")
	private String dni;
	
	
	
	  @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechapedido",nullable=false)
	private Date fechapedido;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public Date getFechapedido() {
		return fechapedido;
	}
	public void setFechapedido(Date fechapedido) {
		this.fechapedido = fechapedido;
	}
	
	
	
	

}

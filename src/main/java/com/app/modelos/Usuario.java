package com.app.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name ="usuario",uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idusu")
	private int codigo;
	
	@Column(name="nomusu")
	private String nomusu;
	
	@Column(name="email")
	private String email;
	
	@Column(name="clave")
	private String clave;
}

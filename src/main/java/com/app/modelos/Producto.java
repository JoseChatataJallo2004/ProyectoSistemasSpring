package com.app.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Controller;


@Entity
@Table(name="productos")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idpro")
	private int idproducto;
	
	@Column(name="nompro")
	private String nomProducto;
	
	
	@Column(name="precio")
	private Double precioPro;
	
	@ManyToOne
   @JoinColumn(name="idmarca")
	private Marca idMarca;
	
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String foto;

	@Column(name="estado")
	private String estado;
	
	public int getIdproducto() {
		return idproducto;
	}

	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
	}

	public String getNomProducto() {
		return nomProducto;
	}

	public void setNomProducto(String nomProducto) {
		this.nomProducto = nomProducto;
	}

	public Double getPrecioPro() {
		return precioPro;
	}

	public void setPrecioPro(Double precioPro) {
		this.precioPro = precioPro;
	}

	public Marca getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(Marca idMarca) {
		this.idMarca = idMarca;
	}



	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	
	
	
	
	
}

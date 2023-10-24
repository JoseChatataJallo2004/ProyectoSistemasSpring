package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.modelos.Marca;
import com.app.modelos.Producto;
import com.app.repository.IMarcaRepository;
import com.app.repository.IProductoRepository;

@Controller
public class TiendaController {

	@Autowired
	private IMarcaRepository marca;
	
	@Autowired
	private IProductoRepository produc;
	
	@GetMapping("/tienda")
	public String listadoProducto(Model model) {
		model.addAttribute("marca",new Marca());
		model.addAttribute("lstMarcaporEstado",marca.findByEstado(1));
		model.addAttribute("lstProductoporEstado",produc.findByEstado(1));

		return "Tienda";
	}
	

	

	
	
	@RequestMapping("/detalle/{idproducto}")
	public String verDetalle(@PathVariable Integer idproducto, Model model) {
	    // Obtener el producto desde la base de datos por su ID
	    Producto producto = produc.findByidproducto(idproducto);

	    if (producto != null) {
	        model.addAttribute("producto", producto); // Usa "producto" en lugar de "produc"
	    } else {
	        // Manejar el caso en que el producto no se encuentra (por ejemplo, mostrar un mensaje de error o redirigir a otra página)
	    }

	    return "DetalleProducto";
	}
}

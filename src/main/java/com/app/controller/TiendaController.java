package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.modelos.Marca;
import com.app.modelos.Producto;
import com.app.repository.IMarcaRepository;
import com.app.repository.IProductoRepository;

@Controller
public class TiendaController {

	@Autowired
	private IMarcaRepository marcas;
	
	@Autowired
	private IProductoRepository produc;
	
	@GetMapping("/tienda")
    public String mostrarTienda(Model model) {
        List<Marca> lstMarcas = marcas.findByEstado(1);
        List<Producto> lstProductos = produc.findByEstado(1);

        model.addAttribute("marca", new Marca());
        model.addAttribute("lstMarcaporEstado", lstMarcas);
        model.addAttribute("lstProductoporEstado", lstProductos);

        return "Tienda";
    }

	
	@PostMapping("/tienda")
	public String filtrarPorMarca(@RequestParam("marcaSeleccionada") String marcaSeleccionada, Model model) {
	    List<Producto> lisProductos;

	    if (marcaSeleccionada.equals("todos")) {
	        lisProductos = produc.findByEstado(1);
	    } else {
	        int marcaId = Integer.parseInt(marcaSeleccionada);
	        Marca marca = marcas.findByCodigo(marcaId);
	        if (marca != null) {
	            lisProductos = produc.findByIdMarca(marca);
	        } else {
	            lisProductos = new ArrayList<>(); 
	        }
	    }

	    List<Marca> lstMarcas = marcas.findByEstado(1);

	    model.addAttribute("marca", new Marca());
	    model.addAttribute("lstMarcaporEstado", lstMarcas);
	    model.addAttribute("lstProductoporEstado", lisProductos);

	    return "Tienda";
	}

	

	
	
	@RequestMapping("/detalle/{idproducto}")
	public String verDetalle(@PathVariable Integer idproducto, Model model) {
	    // Obtener el producto desde la base de datos por su ID
	    Producto producto = produc.findByIdproducto(idproducto);

	    if (producto != null) {
	        model.addAttribute("producto", producto); // Usa "producto" en lugar de "produc"
	    } else {
	        // Manejar el caso en que el producto no se encuentra (por ejemplo, mostrar un mensaje de error o redirigir a otra página)
	    }

	    return "DetalleProducto";
	}
}

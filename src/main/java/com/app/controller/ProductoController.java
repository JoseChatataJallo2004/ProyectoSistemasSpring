package com.app.controller;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.app.modelos.Marca;
import com.app.modelos.Producto;
import com.app.repository.IMarcaRepository;
import com.app.repository.IProductoRepository;

@Controller
public class ProductoController {

	@Autowired
	private IProductoRepository produc;

	@Autowired
	private IMarcaRepository marca;

	@GetMapping("/listar")
	public String listadoProducto(Model model) {
		model.addAttribute("Producto", new Producto());
		model.addAttribute("lstProductos", produc.findAll());
		model.addAttribute("lstMarca", marca.findAll());
		return "listadoProducto";
		//no se cambia
	}

	@PostMapping("/grabarProducto")
	public String grabarPag(@ModelAttribute Producto mar, @RequestParam("image") MultipartFile multi)
			throws IOException {

		String fileName = StringUtils.cleanPath(multi.getOriginalFilename());
		if (fileName.contains("..")) {
			System.out.println("not a a valid file");
		}
		try {
			mar.setFoto(Base64.getEncoder().encodeToString(multi.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		produc.save(mar);
		return "redirect:/listar";
	}

	@PostMapping("/editarProducto")
	public String editar(@ModelAttribute Producto mar) {	
			produc.save(mar);	
		return "redirect:/listar";
	}
	
	@PostMapping("/eliminarProducto")
	public String eliminar(@ModelAttribute Producto mar) {		
		Producto marc =produc.findByidproducto(mar.getIdproducto());	
			produc.delete(marc);	
		return "redirect:/listar";
	}
	
	
}


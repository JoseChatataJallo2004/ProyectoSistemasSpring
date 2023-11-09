package com.app.controller;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.modelos.Producto;
import com.app.repository.IMarcaRepository;
import com.app.repository.IProductoRepository;

@Controller
public class ProductoController {

	@Autowired
	private IProductoRepository produc;

	@Autowired
	private IMarcaRepository marca;

	@GetMapping("/listarProducto")
	public String listadoProducto(Model model) {
		model.addAttribute("Producto", new Producto());
		model.addAttribute("lstProductos", produc.findAll());
		model.addAttribute("lstMarcaporEstado",marca.findByEstado(1));
		//model.addAttribute("lstMarca", marca.findAll());
		return "ListadoProducto";
	}

	@PostMapping("/grabarProducto")
	public String grabarPag(@ModelAttribute Producto mar, @RequestParam("image") MultipartFile multi, RedirectAttributes attribute)
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
		
		try {
			produc.save(mar);
		        attribute.addFlashAttribute("rsuccess", "¡Felicidades! ¡Se registró con éxito!");
		} catch (Exception e) {
			// TODO: handle exception
	        attribute.addFlashAttribute("uniquemarca", "El producto ya está registrada. Por favor, elija una descripción diferente.");

		}
		return "redirect:/listarProducto";
	}


	/*@PostMapping("/editarProducto")
	public String editar(@ModelAttribute Producto mar) {	
			produc.save(mar);	
		return "redirect:/listarProducto";
	}*/
	
	

//	@PostMapping("/editarProducto")
//	public String editar(@ModelAttribute Producto mar) {	
//			produc.save(mar);	
//		return "redirect:/listarProducto";
//	}

	
	@PostMapping("/eliminarProducto")
	public String eliminar(@ModelAttribute Producto mar,RedirectAttributes attribute) {		
		Producto marc =produc.findByIdproducto(mar.getIdproducto());	
			produc.delete(marc);	
			attribute.addFlashAttribute("esuccess","Felicidades se  elimino con éxito!");	
		return "redirect:/listarProducto";
	}
	
	
}


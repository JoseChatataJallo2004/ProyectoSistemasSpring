package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.modelos.Marca;
import com.app.repository.IMarcaRepository;

@Controller
public class MarcaController {

	@Autowired
	private IMarcaRepository marca;
	
	@GetMapping("/listarmarca")
	public String listadoMarca(Model model) {
		model.addAttribute("marca",new Marca());
		model.addAttribute("lstMarca", marca.findAll());
		return "ListadoMarca";
	}
	
	@PostMapping("/grabarmarca")
	public String grabarPag(@ModelAttribute Marca mar, RedirectAttributes attribute) {
		try {
			  marca.save(mar);
		        attribute.addFlashAttribute("rsuccess", "¡Felicidades! ¡Se registró con éxito!");
		} catch (Exception e) {
			// TODO: handle exception
	        attribute.addFlashAttribute("uniquemarca", "La marca ya está registrada. Por favor, elija una descripción diferente.");

		}
		
		
		return "redirect:/listarmarca";
	}
	
	
	@PostMapping("/editar")
	public String editar(@ModelAttribute Marca mar, RedirectAttributes attribute) {	
		
		try {
			  marca.save(mar);
		        attribute.addFlashAttribute("asuccess", "¡Felicidades! ¡Se Actualizo con éxito!");
		} catch (Exception e) {
			// TODO: handle exception
	        attribute.addFlashAttribute("uniquemarca", "La marca ya está registrada. Por favor, elija una descripción diferente.");

		}	
		return "redirect:/listarmarca";
	}
	
	@PostMapping("/eliminar")
	public String eliminar(@ModelAttribute Marca mar, RedirectAttributes attribute) {		
		Marca marc =marca.findByCodigo(mar.getCodigo());	
			marca.delete(marc);	
			attribute.addFlashAttribute("esuccess","Felicidades se  elimino con éxito!");	

		return "redirect:/listarmarca";
	}
	
	
	
	
	
}

package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.modelos.Marca;
import com.app.repository.IMarcaRepository;

@Controller
public class MarcaController {

	@Autowired
	private IMarcaRepository marca;
	
	@GetMapping("/")
	public String listadoMarca(Model model) {
		model.addAttribute("marca",new Marca());
		model.addAttribute("lstMarca", marca.findAll());
		return "ListadoMarca";
	}
	
	@PostMapping("/grabarmarca")
	public String grabarPag(@ModelAttribute Marca mar) {
		marca.save(mar);
		return "redirect:/";
		//jose
	}
	
	
	@PostMapping("/editar")
	public String editar(@ModelAttribute Marca mar) {		
			marca.save(mar);	
		return "redirect:/";
	}
	
	@PostMapping("/eliminar")
	public String eliminar(@ModelAttribute Marca mar) {		
		Marca marc =marca.findByCodigo(mar.getCodigo());	
			marca.delete(marc);	
		return "redirect:/";
	}
	
	
	
	
	
}

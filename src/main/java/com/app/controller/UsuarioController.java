package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.modelos.Usuario;
import com.app.repository.IUsuarioRepository;

@Controller
public class UsuarioController {

	@Autowired
	private IUsuarioRepository user;
	
	
	@GetMapping("/")
	public String formularioLogin(Model model) {
		Usuario usuario=new Usuario();
		model.addAttribute("usuario",usuario);
		return "Login";
	}
	
	
	@PostMapping("/validar")
	public String validarUsuario(@ModelAttribute Usuario usuario, RedirectAttributes attribute) {
		Usuario usuarioValidar = user.findByCodigousuarioAndClave(usuario.getCodigousuario(), usuario.getClave());
		if(usuarioValidar!=null) {
			return "redirect:/listarmarca";
		}else {
	        attribute.addFlashAttribute("error", "Credenciales incorrecta");
			return "redirect:/";
		}
		
	}
	
	
}

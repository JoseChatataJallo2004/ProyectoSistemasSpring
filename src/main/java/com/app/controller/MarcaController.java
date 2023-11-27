package com.app.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.modelos.Marca;
import com.app.repository.IMarcaRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;



@Controller
public class MarcaController {

	@Autowired
	private IMarcaRepository marca;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
	private DataSource dataSource;
	
	
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
	@PostMapping(value="/reporte", params="exportar")
	public void exportar(HttpServletResponse response) throws JRException, SQLException {
		String nombreReporte = "reporte_productos";
		response.setHeader("Content-Disposition", "inline; filename="+nombreReporte+".pdf");
		response.setContentType("application/pdf");
		try {
			String ru = resourceLoader.getResource("classpath:reportes/ReporteProducto.jasper").getURI().getPath();
			HashMap<String, Object>parametros = new HashMap<>();
			JasperPrint jasperPrint = JasperFillManager.fillReport(ru, parametros, dataSource.getConnection()); 
			OutputStream outStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}

package com.app.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Base64;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
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

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
public class ProductoController {

	@Autowired
	private IProductoRepository produc;

	@Autowired
	private IMarcaRepository marca;

	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
	private DataSource dataSource;
	
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
	
	/*@PostMapping(value="/reporte", params="exportar")
	public void exportar(HttpServletResponse response) throws JRException, SQLException {
		String nombreReporte = "reporte_productos";
		response.setHeader("Content-Disposition", "inline; filename="+nombreReporte+".pdf");
		response.setContentType("application/pdf");
		try {
			String ru = resourceLoader.getResource("classpath:reportes/ReporteMarca.jasper").getURI().getPath();
			HashMap<String, Object>parametros = new HashMap<>();
			JasperPrint jasperPrint = JasperFillManager.fillReport(ru, parametros, dataSource.getConnection()); 
			OutputStream outStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	*/
	
}


package com.app.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.modelos.Pedido;
import com.app.modelos.Producto;
import com.app.repository.IPedidoRepository;
import com.app.repository.IProductoRepository;


@Controller
public class CarritoController {

	@Autowired
	private IProductoRepository proce;
	
	@Autowired IPedidoRepository ipedirepo;
	
	
	@GetMapping("/agregarAlCarrito/{idProducto}")
	public String agregarAlCarrito(@PathVariable("idProducto") int idProducto, HttpSession session,RedirectAttributes attribute) {
	    List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");

	    if (carrito == null) {
	        carrito = new ArrayList<>();
	    }

	    // Verificar si el producto ya está en el carrito
	    boolean productoExistente = carrito.stream().anyMatch(producto -> producto.getIdproducto() == idProducto);

	    if (productoExistente) {
	        // Producto ya está en el carrito, mostrar un mensaje de error o notificación
	        // Aquí puedes agregar un mensaje a la sesión para mostrar en la vista
	        //session.setAttribute("errorMensaje", "El producto ya está en el carrito.");
	        //attribute.addFlashAttribute("erroragregar", "¡Error! ¡El producto ya esta en el carrito de compras!");

		//    return "redirect:/tienda"; // Redirige a la página de la tienda
	    } else {
	        // Obtener los detalles del producto desde tu base de datos o fuente de datos
	        Producto producto = proce.findByIdproducto(idProducto);

	        if (producto != null) {
	            // Producto no está en el carrito, agregarlo
	            carrito.add(producto);
	            session.setAttribute("carrito", carrito);
	        } else {
	            // Producto no encontrado en la base de datos o fuente de datos, manejar el error según sea necesario
	            session.setAttribute("errorMensaje", "Error al agregar el producto al carrito.");
	        }
	    }

	    return "redirect:/carrito"; // Redirige a la página del carrito
	}
    
    @GetMapping("/carrito")
    public String mostrarCarrito(Model model, HttpSession session) {
        List<Integer> carrito = (List<Integer>) session.getAttribute("carrito");
        model.addAttribute("productosEnCarrito", carrito);
        return "Carrito"; // Renderiza la página del carrito
    }
    
    
    @GetMapping("/eliminarDelCarrito/{idProducto}")
    public String eliminarDelCarrito(@PathVariable("idProducto") int idProducto, HttpSession session ) {
        List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");

        if (carrito != null) {
            carrito.removeIf(producto -> producto.getIdproducto() == idProducto);
            session.setAttribute("carrito", carrito);
            if(carrito.isEmpty()) {
            	return "redirect:/tienda";
            }
        }
        return "redirect:/carrito"; // Redirige a la página del carrito después de eliminar el producto
    }
    
	@PostMapping("/grabarpedido")
	public String grabarPag(@ModelAttribute Pedido pec, RedirectAttributes attribute,HttpSession session) {
		try {
			//asignar fecha actual
			pec.setFechapedido(new Date());
			
			ipedirepo.save(pec);
	        attribute.addFlashAttribute("sweetAlert", "success");
	        attribute.addFlashAttribute("rsuccess", "¡Felicidades! ¡Su pedido fue realizado  con éxito!");

		        // Limpiar carrito en la sesión después de guardar el pedido exitosamente
		        session.removeAttribute("carrito");
		        return "redirect:/tienda"; // Redirige a la página de la tienda después de guardar el pedido

		} catch (Exception e) {
			// TODO: handle exception
	        attribute.addFlashAttribute("sweetAlert", "error");
	        attribute.addFlashAttribute("uniquemarca", "Hubo un error");

			return "redirect:/carrito";

		}
		
		
	}
	
	

	
    
}


package com.Project.Project.Controladores;

import com.Project.Project.Dao.Service.ProductoService;
import com.Project.Project.Modelo.DetalleOrden;
import com.Project.Project.Modelo.Orden;
import com.Project.Project.Modelo.Producto;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ProductoService productoService;

    //Para almacenar los detalles de la orden
    List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();

    //datos de la orden
    Orden orden = new Orden();
    @GetMapping("")
    public String home(){
        return "usuario/index";
    }

    @GetMapping("/vistacarta")
    public String carta1(){
        return "usuario/nuestraCarta";
    }

    @GetMapping("/promociones")
    public String promocion(){
        return "usuario/promociones";
    }

    @GetMapping("/restaurantes")
    public String restaurante(){
        return "usuario/restaurantes";
    }

    @GetMapping("/login")
    public String iniciar(){
        return "usuario/logIn";
    }

    @GetMapping("/registrar")
    public String registro(){
        return "usuario/register";
    }

    @GetMapping("/reservar")
    public String reservar(){
        return "usuario/reserva";
    }

    @GetMapping("/contactar")
    public String contacto(){
        return "usuario/escribenos";
    }

    @GetMapping("/carta")
    public String vercarta(Model model){
        model.addAttribute("productos", productoService.findAll());
        return "usuario/carta";
    }
    @GetMapping("/verproducto/{id}")
    public String verproducto(@PathVariable Integer id, Model model){
        log.info("Id producto enviado como parámetro {}", id);
        Producto producto = new Producto();
        Optional<Producto> productoOptional = productoService.get(id);
        producto = productoOptional.get();
        model.addAttribute("producto", producto);
        return "usuario/verproducto";
    }

    @PostMapping("/cart")
    public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model){
        DetalleOrden detalleOrden = new DetalleOrden();
        Producto producto = new Producto();
        double sumaTotal = 0;
        Optional<Producto> optionalProducto = productoService.get(id);
        log.info("Producto añadido: {}", optionalProducto.get());
        log.info("Cantidad: {}", cantidad);
        producto = optionalProducto.get();

        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(producto.getPrecio());
        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setTotal(producto.getPrecio()*cantidad);
        detalleOrden.setProducto(producto);

        detalles.add(detalleOrden);

        sumaTotal = detalles.stream().mapToDouble(dt->dt.getTotal()).sum();
        orden.setTotal(sumaTotal);
        model.addAttribute("cart", detalleOrden);
        model.addAttribute("orden", orden);
        return "usuario/carrito";
    }

}

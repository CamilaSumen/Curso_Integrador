package com.Project.Project.Controladores;

import com.Project.Project.Dao.Service.IDetalleOrdenService;
import com.Project.Project.Dao.Service.IOrdenService;
import com.Project.Project.Dao.Service.IUsuarioService;
import com.Project.Project.Dao.Service.ProductoService;
import com.Project.Project.Modelo.DetalleOrden;
import com.Project.Project.Modelo.Orden;
import com.Project.Project.Modelo.Producto;
import com.Project.Project.Modelo.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ProductoService productoService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IOrdenService ordenService;

    @Autowired
    private IDetalleOrdenService detalleOrdenService;

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


    @GetMapping("/reservar")
    public String reservar(){
        return "usuario/reserva";
    }

    @GetMapping("/contactar")
    public String contacto(){
        return "usuario/escribenos";
    }

    @GetMapping("/nosotros")
    public String nosotros(){
        return "usuario/laCantera";
    }

    @GetMapping("/carta")
    public String vercarta(Model model, HttpSession session){
        log.info("Sesión del usuario: {}", session.getAttribute("idusuario"));
        model.addAttribute("productos", productoService.findAll());
        model.addAttribute("sesion", session.getAttribute("idusuario"));
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
        Producto productoo = new Producto();
        double sumaTotal = 0;
        Optional<Producto> optionalProducto = productoService.get(id);
        log.info("Producto añadido: {}", optionalProducto.get());
        log.info("Cantidad: {}", cantidad);
        productoo = optionalProducto.get();

        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(productoo.getPrecio());
        detalleOrden.setNombre(productoo.getNombre());
        detalleOrden.setTotal(productoo.getPrecio()*cantidad);
        detalleOrden.setProducto(productoo);

        //Validar que el producto no se añada dos veces
        Integer idProducto = productoo.getId();
        boolean ingresado = detalles.stream().anyMatch(p -> p.getProducto().getId()==idProducto);
        if(!ingresado){
            detalles.add(detalleOrden);
        }


        sumaTotal = detalles.stream().mapToDouble(dt->dt.getTotal()).sum();
        orden.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        return "usuario/carrito";
    }

    @GetMapping("/delete/cart/{id}")
    public String deleteProductoCart(@PathVariable Integer id, Model model){
        //Lista nueva de productos
        List<DetalleOrden> ordenesNueva = new ArrayList<DetalleOrden>();
        for(DetalleOrden detalleOrden: detalles){
            if(detalleOrden.getProducto().getId()!=id){
                ordenesNueva.add(detalleOrden);
            }
        }
        //poner la nueva lista con los productos restantes
        detalles=ordenesNueva;
        double sumaTotal = 0;
        sumaTotal = detalles.stream().mapToDouble(dt->dt.getTotal()).sum();
        orden.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);

        return "usuario/carrito";
    }

    @GetMapping("/getCart")
    public String getCart(Model model){
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        return "usuario/carrito";
    }

    @GetMapping("/order")
    public String Order(Model model, HttpSession session){

        Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();

        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        model.addAttribute("usuario", usuario);
        return "usuario/resumenorden";
    }

    //guardar la orden
    @GetMapping("/saveOrder")
    public String saveOrder(HttpSession session){
        Date fechaCreacion = new Date();
        orden.setFechaCreacion(fechaCreacion);
        orden.setNumero(ordenService.generarNumeroOrden());

        //usuario
        Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
        orden.setUsuario(usuario);
        ordenService.save(orden);

        //guardar detalles
        for(DetalleOrden dt:detalles){
            dt.setOrden(orden);
            detalleOrdenService.save(dt);
        }
        //limpiar lista y orden
        orden = new Orden();
        detalles.clear();
        return "redirect:/carta";
    }

    //Buscar productos
    @PostMapping("/search")
    public String searchProduct(@RequestParam String nombre, Model model){
        log.info("Nombre del producto: {}", nombre);
        //Se obtienen los productos y se les pasa un filtro para los productos
        List<Producto> productos = productoService.findAll().stream().filter(p -> p.getNombre().contains(nombre)).collect(Collectors.toList());
        model.addAttribute("productos", productos);
        return "usuario/carta";
    }


}

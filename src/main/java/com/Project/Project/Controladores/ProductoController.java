package com.Project.Project.Controladores;

import com.Project.Project.Dao.Service.ProductoService;
import com.Project.Project.Dao.Service.UploadFileService;
import com.Project.Project.Modelo.Producto;
import com.Project.Project.Modelo.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
public class ProductoController {
    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private ProductoService productoService;

    @Autowired
    private UploadFileService upload;

    @GetMapping("productos")
    public String productos(Model model) {
        model.addAttribute("productos", productoService.findAll());
        return "productos/productos";
    }
    @GetMapping("productos/create")
    public String create(){
        return "productos/create";
    }
    @PostMapping("/productos/save")
    public String save(Producto producto, @RequestParam("imgp") MultipartFile file) throws IOException {
        LOGGER.info("Este es el objeto producto {}",producto);
        Usuario u = new Usuario(1L,"", "", "","");
        producto.setUsuario(u);
        //imagen
        if(producto.getId()==null){//cuando se crea un producto
            String nombreImagen = upload.saveImage(file);
            producto.setImagen(nombreImagen);
        } else {
            if(file.isEmpty()){//editamos el producto pero no imagen
                Producto p = new Producto();
                p = productoService.get(producto.getId()).get();
                producto.setImagen(p.getImagen());
            } else {
                String nombreImagen = upload.saveImage(file);
                producto.setImagen(nombreImagen);
            }
        }
        productoService.save(producto);
        return "redirect:/productos";
    }
    @GetMapping("/productos/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Producto producto = new Producto();
        Optional<Producto> optionalProducto = productoService.get(id);
        producto = optionalProducto.get();
        LOGGER.info("Producto buscado: {}", producto);
        model.addAttribute("producto", producto);
        return "productos/edit";
    }
    @PostMapping("/productos/update")
    public String update(Producto producto){
        productoService.update(producto);
        return "redirect:/productos";
    }
    @GetMapping("/productos/delete/{id}")
    public String delete(@PathVariable Integer id){
        productoService.delete(id);
        return "redirect:/productos";
    }

}
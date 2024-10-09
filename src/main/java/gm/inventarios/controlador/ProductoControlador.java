package gm.inventarios.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gm.inventarios.excepcion.RecursosNoEncontrado;
import gm.inventarios.modelo.producto;
import gm.inventarios.servicio.ProductoServicio;

@RestController
//localhost:8080/inventario-app
@RequestMapping("inventario-app")
@CrossOrigin(value = "http://localhost:4200")

public class ProductoControlador {

    private static final Logger logger= LoggerFactory.getLogger(ProductoControlador.class);

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping("/productos")
    public List<producto> obtenerProductos(){
        List<producto> productos= this.productoServicio.ListarProductos();
        logger.info("productos obtenidos");
        productos.forEach(producto -> logger.info(producto.toString()));
        return productos;
    }

    @PostMapping("/productos")
    public producto agregarProducto(@RequestBody producto producto){
        logger.info("Producto a agregar: " + producto);
        return this.productoServicio.guardarProducto(producto);
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<producto> obtenerProductoPorId(
        @PathVariable int id){
        producto producto = this.productoServicio.buscarProductoPorId(id);
        if(producto != null)
            return ResponseEntity.ok(producto);
        else
            throw new RecursosNoEncontrado("No se encontro el ID: " + id);
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<producto> actualizarProducto(
        @PathVariable int id, @RequestBody producto productoRecibido){
            producto producto = this.productoServicio.buscarProductoPorId(id);
            if (producto == null)
                throw new RecursosNoEncontrado("No se encontro el id: " + id);
            producto.setDescripcion(productoRecibido.getDescripcion());
            producto.setPrecio(productoRecibido.getPrecio());
            producto.setExistencia(productoRecibido.getExistencia());
            this.productoServicio.guardarProducto(producto);
            return ResponseEntity.ok(producto);
        }
    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarProducto(@PathVariable int id){
        producto producto = productoServicio.buscarProductoPorId(id);
        if (producto == null)
            throw new RecursosNoEncontrado("No se encontro el id: " + id);
        this.productoServicio.eliminarProductoPorId(producto.getIdProducto());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);

    }
}

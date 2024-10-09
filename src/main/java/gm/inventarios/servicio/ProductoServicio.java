package gm.inventarios.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gm.inventarios.modelo.producto;
import gm.inventarios.repositorio.ProductoRepositorio;

@Service
public class ProductoServicio implements IProductoServicio {

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Override
    public List<producto> ListarProductos() {
        // TODO Auto-generated method stub
        return this.productoRepositorio.findAll();
        
    }

    @Override
    public producto buscarProductoPorId(Integer IdProducto) {
        // TODO Auto-generated method stub
        producto producto = this.productoRepositorio.findById(IdProducto).orElse(null);
        return producto;
    }

    @Override
    public void eliminarProductoPorId(Integer IdProducto) {
        // TODO Auto-generated method stub
        this.productoRepositorio.deleteById(IdProducto);
        
    }

    @Override
    public producto guardarProducto(producto producto) {
        // TODO Auto-generated method stub
        return this.productoRepositorio.save(producto);
    }

}

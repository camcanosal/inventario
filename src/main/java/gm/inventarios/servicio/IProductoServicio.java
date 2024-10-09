package gm.inventarios.servicio;

import java.util.List;

import gm.inventarios.modelo.producto;

public interface IProductoServicio {
    public List<producto> ListarProductos();

    public producto buscarProductoPorId(Integer IdProducto);

    public producto guardarProducto(producto producto);

    public void eliminarProductoPorId(Integer IdProducto);

}

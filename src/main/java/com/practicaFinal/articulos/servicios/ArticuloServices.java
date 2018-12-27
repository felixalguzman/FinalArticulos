package com.practicaFinal.articulos.servicios;

import com.practicaFinal.articulos.entidades.Articulo;
import com.practicaFinal.articulos.repositorios.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticuloServices {

    @Autowired
    ArticuloRepository articuloRepository;


    public void crearArticulo(Articulo articulo) {

        articuloRepository.save(articulo);
    }

    public List<Articulo> buscarTodos(){

        return articuloRepository.findAll();
    }

    public void borrarArticulo(Articulo articulo){

        articuloRepository.delete(articulo);
    }

    public Articulo buscarPorId(Long id) {

        Optional<Articulo> articulo = articuloRepository.findById(id);

        return articulo.orElse(null);

    }

    public List<Articulo> buscarArticulosPorNombre(String nombre) {
        return articuloRepository.findAllByNombreIgnoreCaseContaining(nombre);
    }

    public List<Articulo> paginacionDeArticulos(int offset, int limit) {

        return articuloRepository.buscarArticulosPorPaginacion(offset, limit);
    }

    public List<Articulo> buscarArticulosPorCantidadDisponibleMayorQue(int cantidad) {

        return articuloRepository.findAllByCantidadDisponibleGreaterThanEqual(cantidad);
    }

    public void restarCantidadArticulo(Articulo articulo, int cantidadMenos) {

        articulo.setCantidadDisponible(articulo.getCantidadDisponible() - cantidadMenos);
        articuloRepository.save(articulo);
    }

    public long contarArticulos() {
        
        return articuloRepository.count();
    }

}

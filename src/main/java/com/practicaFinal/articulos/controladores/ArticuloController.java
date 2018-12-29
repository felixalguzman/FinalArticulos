package com.practicaFinal.articulos.controladores;

import com.practicaFinal.articulos.entidades.Articulo;
import com.practicaFinal.articulos.servicios.ArticuloServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticuloController {

    @Autowired
    private ArticuloServices articuloServices;

    @GetMapping("/articulos")
    public List<Articulo> articulos() {

        return articuloServices.buscarTodos();
    }

    @GetMapping("/articulos/nombre/{nombre}")
    public List<Articulo> articulosPorNombre(@PathVariable String nombre) {

        return articuloServices.buscarArticulosPorNombre(nombre);
    }

    @RequestMapping(value = "/articulos", method = RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<Articulo> crearArticulo(@RequestBody Articulo articulo) {

        articuloServices.crearArticulo(articulo);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/articulos", method = RequestMethod.PUT, consumes = {"application/json"})
    public ResponseEntity<Articulo> actualizarArticulo(@RequestBody Articulo articulo) {

        articuloServices.crearArticulo(articulo);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/articulos", method = RequestMethod.DELETE, params = {"id"})
    public ResponseEntity<Articulo> borrarArticulo(@RequestParam("id") Long id) {
        Articulo articulo = articuloServices.buscarPorId(id);
        articuloServices.borrarArticulo(articulo);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/articulos/comprar", method = RequestMethod.PUT, params = {"id", "cantidad"})
    public ResponseEntity<Articulo> comprarArticulos(@RequestParam("id") Long id, @RequestParam("cantidad") int cantidad) {

        Articulo articulo = articuloServices.buscarPorId(id);

        articuloServices.restarCantidadArticulo(articulo, cantidad);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/articulos/paginacion", method = RequestMethod.GET, params = {"limit", "offset"}, produces = {"application/json"})
    public List<Articulo> articulosPaginacion(@RequestParam("limit") int limit, @RequestParam("offset") int offset) {

        Pageable pageable = PageRequest.of(offset, limit);
        return articuloServices.paginacionDeArticulos(pageable);
    }

    @GetMapping("/articulos/{id}")
    public Articulo buscarPorId(@PathVariable Long id) {

        return articuloServices.buscarPorId(id);
    }

    @GetMapping("/articulos/cantidad")
    public int contarArticulos(){

        return (int) articuloServices.contarArticulos();
    }


}

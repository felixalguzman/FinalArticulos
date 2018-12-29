package com.practicaFinal.articulos.repositorios;

import com.practicaFinal.articulos.entidades.Articulo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloPaginacionRepository extends PagingAndSortingRepository<Articulo, Long> {

    Page<Articulo> findAll(Pageable pageable);

}

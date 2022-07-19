package co.net.solucionesrh.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.net.solucionesrh.modelos.Tipos;

@Repository
public interface InterfaceTipos extends CrudRepository<Tipos, Integer>  {

    @Query(value="SELECT * FROM tbl_tipos_nave t WHERE LOWER(t.nombre) LIKE %nombre%")
	public List<Tipos> findByNombre(@Param("nombre") String nombre);


    // @Transactional(readOnly = true)
    // Optional<Tipos> findByNombre(String nombre);


}

package co.net.solucionesrh.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.net.solucionesrh.modelos.Tipos;

@Repository
public interface InterfaceTipos extends JpaRepository<Tipos, Integer>  {

    // ----------------- Buscar tipo de nave -----------------
    //Con Like por nombre   
    // @Transactional(readOnly = true)
    @Query(nativeQuery = true, value=" SELECT * FROM tbl_tipos_nave WHERE nombre LIKE %:nombre% ")
	public List<Tipos> findByLikeName(String nombre);

    //Con Like para filtro avanzado   
    // @Transactional(readOnly = true)
    @Query(nativeQuery = true, value=" SELECT * FROM tbl_tipos_nave WHERE nombre LIKE %:filtro% OR descripcion LIKE %:filtro% OR estado LIKE %:filtro%")
	public List<Tipos> filterAdvanced(String filtro);

    //Por nombre
    Optional<Tipos> findByNombre(String nombre);

    // ----------------- Actualizar tipo de nave -----------------
    //Eliminar (inactivar) logicamente
    @Query(nativeQuery = true, value=" UPDATE tbl_tipos_nave SET estado=0 WHERE codtiponave =:id ")
	public Optional<Tipos> deleteLogico(Integer id);

    //Restaurar (activar) logicamente
    @Query(nativeQuery = true, value=" UPDATE tbl_tipos_nave SET estado=1 WHERE codtiponave =:id ")
	public Optional<Tipos> reactivateLogico(Integer id);

}

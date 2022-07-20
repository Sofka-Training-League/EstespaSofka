package co.net.solucionesrh.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.net.solucionesrh.modelos.Naves;

@Repository
public interface InterfaceNaves extends JpaRepository<Naves, Long>  {

// ----------------- Buscar tipo de nave -----------------
    //Con Like por nombre   
    // @Transactional(readOnly = true)
    @Query(nativeQuery = true, value=" SELECT * FROM tbl_naves WHERE nombre LIKE %:nombre% ")
	public List<Naves> findByLikeName(String nombre);

    //Con Like para filtro avanzado   
    // @Transactional(readOnly = true)
    @Query(nativeQuery = true, value=" SELECT * FROM tbl_naves WHERE nombre LIKE %:filtro% OR objetivo LIKE %:filtro% OR paisorigen LIKE %:filtro%")
	public List<Naves> filterAdvanced(String filtro);

    //Por nombre
    Optional<Naves> findByNombre(String nombre);

    //Por pais de Origen
    Optional<Naves> findByPaisorigen(String paisorigen);

    // // // --------- Actualizar estado y fechas de mision  ---------
    // @Query(nativeQuery = true, value=" UPDATE tbl_naves SET inexploracion=:inexploracion, estado=2 WHERE codnave=:id ")
	// public Optional<Naves> startLaunch(Long id, String inexploracion);

    // // //Restaurar (activar) logicamente
    // @Query(nativeQuery = true, value=" UPDATE tbl_naves SET estado=1 WHERE codnave=:id ")
	// public Optional<Naves> endLaunch(Long id);


}

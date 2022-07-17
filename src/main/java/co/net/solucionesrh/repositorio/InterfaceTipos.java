package co.net.solucionesrh.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.net.solucionesrh.modelos.Tipos;

@Repository
public interface InterfaceTipos extends CrudRepository<Tipos, Integer>  {

}

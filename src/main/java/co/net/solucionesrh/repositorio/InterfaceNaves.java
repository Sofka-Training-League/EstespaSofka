package co.net.solucionesrh.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.net.solucionesrh.modelos.Naves;

@Repository
public interface InterfaceNaves extends CrudRepository<Naves, Long>  {

}

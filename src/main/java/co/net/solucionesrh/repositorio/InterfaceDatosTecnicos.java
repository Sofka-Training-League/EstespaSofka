package co.net.solucionesrh.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.net.solucionesrh.modelos.DatosTecnicos;

@Repository
public interface InterfaceDatosTecnicos extends CrudRepository<DatosTecnicos, Long>  {

}

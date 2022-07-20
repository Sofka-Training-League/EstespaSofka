package co.net.solucionesrh.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.net.solucionesrh.modelos.Estados;

@Repository
public interface InterfaceEstados extends JpaRepository<Estados, Integer>  {


}
